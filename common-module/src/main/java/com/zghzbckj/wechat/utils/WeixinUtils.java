package com.zghzbckj.wechat.utils;




import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JackSonJsonUtils;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.entity.BckjDicKeys;
import net.sf.json.JSONException;
import com.zghzbckj.manage.entity.SysWxconfig;
import com.zghzbckj.wechat.WechatConstants;
import com.zghzbckj.wechat.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公众平台通用接口工具类
 *
 * @date 2013-09-10
 */
public class WeixinUtils {
private static final Logger log = Logger.getLogger(WeixinUtils.class);
    public final static String oauth2_1_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public final static String oauth2_2_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public final static String getuser_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public final static String filter_url="https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN";

    public static JSONObject httpRequest(String requestUrl,
                                         String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                    .openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            // log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            // log.error("https request error:{}", e);
        }
        return jsonObject;
    }


    /**
     * 网页授权认证
     * @param wxCode
     * @param code
     * @return
     */
    public static WeixinOauth2Token getOauth2AccessToken(String wxCode, String code) {
        SysWxconfig config=  CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxCode,SysWxconfig.class);
        if(null==config){
            return new WeixinOauth2Token();
        }
        String  requestUrl=oauth2_1_url.replace("APPID", config.getWeAppid()).replace("SECRET", config.getWeSecrect()).replace("CODE", code);
        //发送请求获取网页授权凭证
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
        WeixinOauth2Token   wxo=new WeixinOauth2Token();
        wxo.setAccessToken(jsonObject.getString("access_token"));
        wxo.setExpiresIn(jsonObject.getInt("expires_in"));
        wxo.setRefreshToken(jsonObject.getString("refresh_token"));
        wxo.setOpenId(jsonObject.getString("openid"));
        wxo.setScope(jsonObject.getString("scope"));
        return wxo;

    }

    /**
     * 获取用户的基本信息
     * @param weCode
     * @param openId
     * @return
     */
    public static WxUserModel getSNSUserInfo(String oathToken, String weCode, String openId) {

        String requestUrl=oauth2_2_url.replace("ACCESS_TOKEN", oathToken).replace("OPENID", openId);
        WxUserModel snsuserinfo=new WxUserModel();
        //通过网页授权获取用户信息
        JSONObject jsonObject=HttpsRequest.doGet(requestUrl, null);
        snsuserinfo.setOpenId(jsonObject.getString("openid"));
        snsuserinfo.setNickname(jsonObject.getString("nickname").replaceAll("[\ue000-\uefff]",""));
        snsuserinfo.setSex(jsonObject.getString("sex"));
        snsuserinfo.setCountry(jsonObject.getString("country"));
        snsuserinfo.setProvince(jsonObject.getString("province"));
        snsuserinfo.setCity(jsonObject.getString("city"));
        snsuserinfo.setHeadimgurl(jsonObject.getString("headimgurl"));
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + weCode, AccessToken.class);
        if(null == accessToken) {
            return null;
        }
         requestUrl=getuser_info_url.replace("ACCESS_TOKEN", accessToken.getToken()).replace("OPENID", openId);
        //通过普通接口获取用户信息
         jsonObject=HttpsRequest.doGet(requestUrl, null);
          log.info(jsonObject+"=--");
         snsuserinfo.setSubscribe(Integer.valueOf(jsonObject.getString("subscribe")));
        return snsuserinfo;
    }

    //    判断是否是微信浏览器
    static Pattern patten = Pattern.compile("MicroMessenger/(\\d+).+");
    public static String xcu_msg_url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
    public static boolean xcuSendBackMsg(Map<String,Object> map, String accessToken){
        int result = 0;
        String url = xcu_msg_url.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = JSONObject.fromObject(map).toString();
        // 调用接口发送数据到微信服务器
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
       log.info(jsonObject.toString()+"------------------------------------------mesBack");
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.info(jsonObject.toString()+"errorcode==="+result);
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 用来判断微信是否是5.0版本以上，否则不支持购买
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午11:12:30 Jack Exp $
     */
    public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");

        if (!TextUtils.isEmpty(userAgent)) {
            Matcher m = patten.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && Integer.parseInt(version) >= 5);
        }
        return false;
    }

    /**
     * <p>方法:getAccessToken 根据微信的appid从缓存中获取accestoken </p>
     * <ul>
     * <li> @param Appid 微信的appId</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/18 10:46  </li>
     * </ul>
     */
    public static String getAccessToken(String appId) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + appId, AccessToken.class);
        if (null == accessToken) {
            return "";
        }
        return accessToken.getToken();
    }


    /**
     * 调用微信接口进行关键字过滤
     * coder wangangaanng
     */
   public static Map<String, Object> filterContent(Map<String, Object> mapData, String weCode){
       int result = 0;
       AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + weCode, AccessToken.class);
       String requestUrl=filter_url.replace("ACCESS_TOKEN", accessToken.getToken());
       String jsonMenu = JSONObject.fromObject(mapData).toString();
       // 调用接口发送数据到微信服务器
       JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
       log.info(jsonObject.toString()+"------------------------------------------mesBack");
       if (null != jsonObject) {
           if (0 != jsonObject.getInt("errcode")) {
               result = jsonObject.getInt("errcode");
               log.info(jsonObject.toString()+"errorcode==="+result);
           }
       }
       String jsonStr = jsonObject.toString();
       Map<String, Object> resMap = JsonUtil.jsonToMap(jsonStr);
       return resMap;
    }



    /**
     * <p>
     * 产生随机字符串
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午11:19:30 Jack Exp $
     */
    public static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * <p>
     * 传入内容进行SHA1签名
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午12:23:54 Jack Exp $
     */
    public static String sign(String mess) {
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(mess.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * 微信支付的签名
     *
     * @param characterEncoding
     * @param parameters
     * @param apiKey
     * @return
     */
    public static String createSign(String characterEncoding,
                                    SortedMap<Object, Object> parameters, String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding)
                .toUpperCase();
        return sign;
    }

    /**服务号 根据openId 获取信息*/
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static WxUserModel getUserInfo(String access_token, String openId){
        WxUserModel user = null;
        String requestUrl = USER_INFO_URL.replace("ACCESS_TOKEN", access_token);
        requestUrl = requestUrl.replace("OPENID", openId);
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl,null);
        // 如果请求成功
        if (null != jsonObject) {
            System.out.println("jsonObject============"+jsonObject);
            try{
                if (TextUtils.isEmpty(jsonObject.get("errcode"))) {
                    user = new WxUserModel();
                    String _nickName = "";
                    if (jsonObject.has("nickname")) {
                        _nickName = jsonObject.getString("nickname");
                        _nickName = EmojiFilterUtils.filterEmoji(_nickName);
                    }
                    user.setNickname(_nickName);
                    if (jsonObject.has("sex")) {
                        user.setSex(jsonObject.getString("sex"));
                    }
                    if (jsonObject.has("city")) {
                        user.setCity(jsonObject.getString("city"));
                    }
                    if (jsonObject.has("country")) {
                        user.setCountry(jsonObject.getString("country"));
                    }
                    if (jsonObject.has("province")) {
                        user.setProvince(jsonObject.getString("province"));
                    }
                    if (jsonObject.has("headimgurl")) {
                        user.setHeadimgurl(jsonObject.getString("headimgurl"));
                    }
                    if (jsonObject.has("subscribe_time")) {
                        user.setSubscribe_time(transferLongToDate("yyyy-MM-dd HH:mm:ss",
                                new Long(jsonObject.getString("subscribe_time"))));
                    }
                    if (jsonObject.has("remark")){
                        user.setRemark(jsonObject.getString("remark"));
                    }
                    if (jsonObject.has("groupid")){
                        user.setGroupid(jsonObject.getString("groupid"));
                    }
                    if(jsonObject.has("unionid")) {
                        user.setUnionid(jsonObject.getString("unionid"));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        user.setOpenId(openId);
        return user;
    }

    /**
     * <p>
     * 字节转16进制
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午12:00:04 Jack Exp $
     */
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    /**
     * 调用微信接口，获取微信的AccessToken
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) throws Exception {
        AccessToken accessToken = null;
        String requestUrl = WechatConstants.WechatApiURL.URL_ACCESSTOKEN.replace("APPID", appid).replace(
                "APPSECRET", appsecret);
        try {
            JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
            // 如果请求成功
            if (!TextUtils.isEmpty(jsonObject)) {
                if (jsonObject.containsKey("errcode")) {
                    //表示失败
                    throw new Exception(jsonObject.getString("errmsg"));
                } else {
                    accessToken = new AccessToken();
                    if (jsonObject.containsKey("access_token")) {
                        accessToken.setToken(jsonObject.getString("access_token"));
                    }
                    if (jsonObject.containsKey("expires_in")) {
                        Date d = new Date();
                        accessToken.setGetTime(d.getTime() / 1000);
                        accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    }
                    return accessToken;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    /**
     * <p>方法:getMiniProgrammAccessToken 获取小程序的accessToken </p>
     * <ul>
     * <li> @param appid TODO</li>
     * <li> @param appsecret TODO</li>
     * <li>@return AccessToken  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/14 11:44  </li>
     * </ul>
     */
    public static AccessToken getMiniProgrammAccessToken(String appid, String appsecret) throws Exception {
        AccessToken accessToken = null;
        String requestUrl = WechatConstants.WechatApiURL.URL_MINIPROGRAMM_ACCESSTOKEN.replace("APPID", appid).replace(
                "APPSECRET", appsecret);
        try {
            JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
            // 如果请求成功
            if (!TextUtils.isEmpty(jsonObject)) {
                if (jsonObject.containsKey("errcode")) {
                    //表示失败
                    throw new Exception(jsonObject.getString("errmsg"));
                } else {
                    accessToken = new AccessToken();
                    if (jsonObject.containsKey("access_token")) {
                        accessToken.setToken(jsonObject.getString("access_token"));
                    }
                    if (jsonObject.containsKey("expires_in")) {
                        Date d = new Date();
                        accessToken.setGetTime(d.getTime() / 1000);
                        accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    }
                    return accessToken;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    /**
     * <p>方法:getTicket 获取jsticket </p>
     * <ul>
     * <li> @param accessToken TODO</li>
     * <li>@return JSTickets  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/25 15:35  </li>
     * </ul>
     */
    public static JSTickets getTicket(String accessToken) throws Exception {
        JSTickets jsticket = null;
        String requestUrl = WechatConstants.WechatApiURL.URL_JSTICKET.replace("ACCESSTOKEN",
                accessToken);
        try {
            JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
            // 如果请求成功
            if (!TextUtils.isEmpty(jsonObject)) {
                if (jsonObject.containsKey("errcode") && !"0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                    //表示失败
                    throw new Exception(jsonObject.getString("errmsg"));
                } else {
                    jsticket = new JSTickets();
                    if (jsonObject.containsKey("ticket")) {
                        jsticket.setTicket(jsonObject.getString("ticket"));
                    }
                    if (jsonObject.containsKey("expires_in")) {
                        Date d = new Date();
                        jsticket.setGetTime(d.getTime() / 1000);
                        jsticket.setExpiresIn(jsonObject.getInt("expires_in"));
                    }
                    return jsticket;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }


    public static String long2Short(String longurl,String wxCode){
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if(null == accessToken) {
            return null;
        }
        String sUrl ="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
        try {
            sUrl = sUrl.replace("ACCESS_TOKEN", accessToken.getToken());
            String param = "{\"action\":\"long2short\","
                    + "\"long_url\":\""+longurl+"\"}";
            JSONObject jsonObject = httpRequest(sUrl, "POST", param);
//            System.err.println("jsonObject:"+jsonObject);
            boolean containsValue = jsonObject.containsKey("errcode");
            if(containsValue){
                String errcode = jsonObject.getString("errcode");
                if(errcode.equals("0")){
                    return jsonObject.getString("short_url");
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>方法:doCreateTempQRCODE 传入有效期和参数，生成临时微信二维码 </p>
     * <ul>
     * <li> @param accessToken TODO</li>
     * <li> @param liveleng TODO</li>
     * <li> @param sceneStr TODO</li>
     * <li>@return WeChatQRCode  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/25 15:53  </li>
     * </ul>
     */
    public static WeChatQRCode doCreateTempQRCODE(String wxAppid, int liveleng, int scence) throws Exception {
        WeChatQRCode jsticket = null;
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxAppid, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        log.info("=====" + accessToken.getToken());
        String requestUrl = WechatConstants.WechatApiURL.URL_QRCODE.replace("ACCESS_TOKEN",
                accessToken.getToken());
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("expire_seconds", liveleng);
            params.put("action_name", "QR_SCENE");
            Map<String, Integer> scenVal = new HashMap<String, Integer>(1);
            scenVal.put("scene_id", scence);
            Map<String, Object> _scene = new HashMap<String, Object>(1);
            _scene.put("scene", scenVal);
            params.put("action_info", _scene);
            String jsonStr = JSONObject.fromObject(params).toString();
            String result = HttpsRequest.doPost(requestUrl, jsonStr);
            log.error("=========" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            // 如果请求成功
            if (!TextUtils.isEmpty(jsonObject)) {
                if (!jsonObject.containsKey("ticket")) {
                    //表示失败
                    throw new Exception("生成临时二维码失败");
                } else {
                    jsticket = new WeChatQRCode();
                    if (jsonObject.containsKey("ticket")) {
                        jsticket.setTicket(jsonObject.getString("ticket"));
                    }
                    if (jsonObject.containsKey("expire_seconds")) {
                        Date d = new Date();
                        jsticket.setGetTime(d.getTime() / 1000);
                        jsticket.setExpireSeconds(jsonObject.getInt("expire_seconds"));
                    }
                    if (jsonObject.containsKey("url")) {
                        jsticket.setUrl(jsonObject.getString("url"));
                    }
                    return jsticket;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return null;
    }


    /**
     * <p>方法:createMenu 创建菜单 </p>
     * <ul>
     * <li> @param btns 所有的菜单</li>
     * <li> @param wxCode 微信编码</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/12 15:16  </li>
     * </ul>
     */
    public static boolean createMenu(List<Map<String, Object>> btns, String wxCode) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return false;
        }
        // 拼装创建菜单的url
        String requestUrl = WechatConstants.WechatApiURL.URL_MENUCREATE.replace("ACCESS_TOKEN", accessToken.getToken());
        Map<String, Object> menuMap = new HashMap<String, Object>(1);
        menuMap.put("button", btns.toArray());
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menuMap).toString();
        // 调用接口创建菜单
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (null != jsonObject) {
            if (jsonObject.containsKey("errcode")) {
                if ("0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return false;
    }


    /**
     * <p>方法:sendKfTxtMsg 发送客服文本消息 </p>
     * <ul>
     * <li> @param accessToken TODO</li>
     * <li> @param openId TODO</li>
     * <li> @param content TODO</li>
     * <li> @param programmAppId TODO</li>
     * <li> @param programmPageUrl TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/25 16:35  </li>
     * </ul>
     */
    public static boolean sendKfTxtMsg(String accessToken, String openId, String content, String programmAppId, String programmPageUrl) {
        String requestUrl = WechatConstants.WechatApiURL.URL_KF_MSG.replace("ACCESS_TOKEN", accessToken);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("msgtype", "text");
        Map<String, String> _map = new HashMap<String, String>(1);
        if (!TextUtils.isEmpty(programmAppId) && !TextUtils.isEmpty(programmPageUrl)) {
            _map.put("content", content + "<a href=\"#\" data-miniprogram-appid=\"" + programmAppId + "\" data-miniprogram-path=\"" + programmPageUrl + "\">点击跳小程序</a>");
        } else {
            _map.put("content", content);
        }
        params.put("text", _map);
        String jsonMenu = JSONObject.fromObject(params).toString();
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (null != jsonObject) {
            if (jsonObject.containsKey("errcode")) {
                if ("0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return false;
    }


    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec * 1000);
        return sdf.format(date);
    }

    /**
     * <p>
     * 根据静默方式的授权来获取关注用户的信息，若无关注，则返回subscribe为0或者null
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-9-15 下午10:56:37 Jack Exp $
     */
    public static WxUserModel getMembByUserInfo(String code, String wxState) {
        if (TextUtils.isEmpty(code)) {
            return null;
        }
        SysWxconfig wxconfig = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxState, SysWxconfig.class);
        if (null == wxconfig) {
            return null;
        }
        WxUserModel user = null;

        String requestUrl = WechatConstants.WechatApiURL.URL_USER_CODE.replace("APPID", wxconfig.getWeAppid())
                .replace("SECRET", wxconfig.getWeSecrect()).replace("CODE", code);
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
        if (null != jsonObject) {
            if (jsonObject.containsKey("errcode") && !"0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                return null;
            }
            user = new WxUserModel();
            String access_token = "";
            String openId = "";
            if (jsonObject.containsKey("access_token")) {
                access_token = jsonObject.getString("access_token");
            }
            if (jsonObject.containsKey("openid")) {
                openId = jsonObject.getString("openid");
            }
            user.setOpenId(openId);
            requestUrl = WechatConstants.WechatApiURL.URL_USERINFO_SNSAPIUSERINFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
            jsonObject = HttpsRequest.doGet(requestUrl, null);
            if (null != jsonObject) {
                //获取信息失败
                if (jsonObject.containsKey("errcode") && !"0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                    return user;
                } else {
                    if (jsonObject.containsKey("nickname")) {
                        user.setNickname(EmojiFilterUtils.filterEmoji(jsonObject.getString("nickname")));
                    }
                    if (jsonObject.containsKey("sex")) {
                        user.setSex(jsonObject.getString("sex"));
                    }
                    if (jsonObject.containsKey("province")) {
                        user.setProvince(jsonObject.getString("province"));
                    }
                    if (jsonObject.containsKey("city")) {
                        user.setCity(jsonObject.getString("city"));
                    }
                    if (jsonObject.containsKey("country")) {
                        user.setCountry(jsonObject.getString("country"));
                    }
                    if (jsonObject.containsKey("headimgurl")) {
                        user.setHeadimgurl(jsonObject.getString("headimgurl"));
                    }
                    if (jsonObject.containsKey("privilege")) {
                        user.setPrivilege(jsonObject.getString("privilege"));
                    }
                    if (jsonObject.containsKey("unionid")) {
                        user.setUnionid(jsonObject.getString("unionid"));
                    }
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * 非静默模式获取用户相关信息
     *
     * @param code
     * @return
     */
    public static WxUserModel getMembByBase(String code, String wxState) {
        if (TextUtils.isEmpty(code)) {
            return null;
        }
        SysWxconfig wxconfig = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxState, SysWxconfig.class);
        if (null == wxconfig) {
            return null;
        }
        WxUserModel user = null;
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxconfig.getWeAppid(), AccessToken.class);
        String requestUrl = WechatConstants.WechatApiURL.URL_USER_CODE.replace("APPID", wxconfig.getWeAppid())
                .replace("SECRET", wxconfig.getWeSecrect()).replace("CODE", code);
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, null);
        if (null != jsonObject) {
            if (jsonObject.containsKey("errcode") && !"0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                return null;
            }
            user = new WxUserModel();
            String openId = "";
            if (jsonObject.containsKey("openid")) {
                openId = jsonObject.getString("openid");
            }
            user.setOpenId(openId);
            requestUrl = WechatConstants.WechatApiURL.URL_USERINFO_SNSAPIUSERINFO.replace("ACCESS_TOKEN", accessToken.getToken()).replace("OPENID", openId);
            jsonObject = HttpsRequest.doGet(requestUrl, null);
            if (null != jsonObject) {
                //获取信息失败
                if (jsonObject.containsKey("errcode") && !"0".equalsIgnoreCase(jsonObject.getString("errcode"))) {
                    return user;
                } else {
                    if (jsonObject.containsKey("nickname")) {
                        user.setNickname(EmojiFilterUtils.filterEmoji(jsonObject.getString("nickname")));
                    }
                    if (jsonObject.containsKey("sex")) {
                        user.setSex(jsonObject.getString("sex"));
                    }
                    if (jsonObject.containsKey("province")) {
                        user.setProvince(jsonObject.getString("province"));
                    }
                    if (jsonObject.containsKey("city")) {
                        user.setCity(jsonObject.getString("city"));
                    }
                    if (jsonObject.containsKey("country")) {
                        user.setCountry(jsonObject.getString("country"));
                    }
                    if (jsonObject.containsKey("headimgurl")) {
                        user.setHeadimgurl(jsonObject.getString("headimgurl"));
                    }
                    if (jsonObject.containsKey("privilege")) {
                        user.setPrivilege(jsonObject.getString("privilege"));
                    }
                    if (jsonObject.containsKey("unionid")) {
                        user.setUnionid(jsonObject.getString("unionid"));
                    }
                    return user;
                }
            }
        }
        return null;
    }


    /**
     * <p>方法:sendTemplateMsg 发下哦那个消息模板，传递过来的Map 已根据接口要求组织好数据了 </p>
     * <ul>
     * <li> @param map 包含 touser，template_id，url，topcolor和data，其中data是Map<String,Map<String,Object>>的数据对象</li>
     * <li> @param wxCode 微信别名</li>
     * <li>@return Map<String,Object> errcode:0 成功，msgid:消息编号，errmsg：错误信息  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/10 14:26  </li>
     * </ul>
     */
    public static Map<String, Object> sendTemplateMsg(Map<String, Object> map, String wxCode) {

        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        String url = WechatConstants.WechatApiURL.URL_TEMPMSG.replace("ACCESS_TOKEN", accessToken.getToken());
        String jsonMenu = JSONObject.fromObject(map).toString();
        String result = HttpsRequest.doPost(url, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }

    public static Map<String, Object> sendTemplateMsgWithAccessToken(Map<String, Object> params, String AccessToken) {
        String url = WechatConstants.WechatApiURL.URL_TEMPMSG.replace("ACCESS_TOKEN", AccessToken);
        String str = JSONObject.fromObject(params).toString();
        String result = HttpsRequest.doPost(url, str);
        return JsonUtil.jsonToMap(result);
    }

    /**
     * <p>方法:sendBackMsg 发送客服消息 </p>
     * <ul>
     * <li> @param param TODO</li>
     * <li> @param wxCode TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/10 14:55  </li>
     * </ul>
     */
    public static Map<String, Object> sendBackMsg(Map<String, Object> param, String wxCode) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        String url = WechatConstants.WechatApiURL.URL_CUSTMSG.replace("ACCESS_TOKEN", accessToken.getToken());
        String str = JSONObject.fromObject(param).toString();
        String result = HttpsRequest.doPost(url, str);
        return JsonUtil.jsonToMap(result);
    }

    // {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}

    /**
     * <p>方法:getUserList 获取关注着列表 </p>
     * <ul>
     * <li> @param wxCode TODO</li>
     * <li> @param nextOpenId TODO</li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/10 14:56  </li>
     * </ul>
     */
    public static Map<String, Object> getUserList(String wxCode, String nextOpenId) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        String url = WechatConstants.WechatApiURL.URL_USERLIST.replace("ACCESS_TOKEN", accessToken.getToken()).replace("NEXT_OPENID", nextOpenId);
        JSONObject jsonObject = HttpsRequest.doGet(url, "");
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != jsonObject) {
            try {
                if (!TextUtils.isEmpty(jsonObject.get("errcode"))) {
                    map.put("errcode", jsonObject.getString("errcode"));
                    map.put("errmsg", jsonObject.getString("errmsg"));
                } else {
                    map.put("errcode", "0");
                    map.put("errmsg", "");
                    map.put("total", jsonObject.getString("total"));
                    map.put("count", jsonObject.getString("count"));
                    List<String> list = new ArrayList<String>();
                    JSONObject datas = (JSONObject) jsonObject.get("data");
                    JSONArray openidArray = datas.getJSONArray("openid");
                    for (Object object : openidArray) {
                        list.add(object.toString());
                    }
                    map.put("data", list);
                    map.put("next_openid", jsonObject.getString("next_openid"));
                }
            } catch (JSONException e) {
                System.out.println("获取关注着列表失败" + e.getMessage());
            }
        }
        return map;
    }


    /**
     * <p>方法:getSingUserInfo 传入指定的OpenId获取用户基本信息 </p>
     * <ul>
     * <li> @param wxCode TODO</li>
     * <li> @param openId TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/10 15:13  </li>
     * </ul>
     */
    public static Map<String, Object> getSingUserInfo(String wxCode, String openId) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        String requestUrl = WechatConstants.WechatApiURL.URL_USERLIST_ONE.replace("ACCESS_TOKEN", accessToken.getToken()).replace("OPENID", openId);

        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, "");
        // 如果请求成功
        if (null != jsonObject) {
            try {
                if (!TextUtils.isEmpty(jsonObject.get("errcode"))) {
                    map.put("errcode", jsonObject.getString("errcode"));
                    map.put("errmsg", jsonObject.getString("errmsg"));
                } else {
                    map.put("errcode", "0");
                    map.put("errmsg", "");
                    map.put("subscribe", jsonObject.getString("subscribe"));
                    map.put("openid", jsonObject.getString("openid"));
                    map.put("nickname", jsonObject.getString("nickname"));
                    map.put("sex", jsonObject.getString("sex"));
                    map.put("city", jsonObject.getString("city"));
                    map.put("country", jsonObject.getString("country"));
                    map.put("province", jsonObject.getString("province"));
                    map.put("language", jsonObject.getString("language"));
                    map.put("headimgurl", jsonObject.getString("headimgurl"));
                    map.put("subscribe_time",
                            jsonObject.getString("subscribe_time"));
                    map.put("remark", jsonObject.getString("remark"));
                    map.put("groupid", jsonObject.getString("groupid"));
                    if (jsonObject.has("unionid")) {
                        map.put("unionid", jsonObject.getString("unionid"));
                    }
                }
            } catch (JSONException e) {
                System.out.println("获取关注着列表失败" + e.getMessage());
            }
        }
        return map;
    }

    /**
     * <p>
     * 统一下单接口，返回订单号
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午12:16:08 Jack Exp $
     */
    public static UnifiedOrderBack unifiedOrder(UnifiedOrder order) {
        UnifiedOrderBack orderback = new UnifiedOrderBack();
        String tmp = "";
        // System.out.println("===统一下单===" + order.toxml());
        String result = HttpsRequest.doPost(WechatConstants.WechatApiURL.URL_UNIFIEDORDER, order.toxml());
        // System.out.println("====统一下单返回===" + result);
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        // 组成完成的xml数据，提供给xmlutils去解析
        Map<String, String> map = XMLUtils.transXMLParams(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmls>" + result
                        + "</xmls>", "xml", new String[]{"return_code",
                        "return_msg", "appid", "mch_id", "device_info", "nonce_str", "sign",
                        "result_code", "err_code", "err_code_des", "prepay_id", "trade_type", "code_url"});
        if (!TextUtils.isEmpty(map.get("return_code"))) {
            tmp = map.get("return_code");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setReturn_code(tmp);
        }
        if (!TextUtils.isEmpty(map.get("return_msg"))) {
            tmp = map.get("return_msg");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setReturn_msg(tmp);
        }
        if (!TextUtils.isEmpty(map.get("appid"))) {
            tmp = map.get("appid");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setAppid(tmp);
        }
        if (!TextUtils.isEmpty(map.get("mch_id"))) {
            tmp = map.get("mch_id");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setMch_id(tmp);
        }
        if (!TextUtils.isEmpty(map.get("device_info"))) {
            tmp = map.get("device_info");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setDevice_info(tmp);
        }
        if (!TextUtils.isEmpty(map.get("nonce_str"))) {
            tmp = map.get("nonce_str");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setNonce_str(tmp);
        }
        if (!TextUtils.isEmpty(map.get("sign"))) {
            tmp = map.get("sign");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setSign(tmp);
        }
        if (!TextUtils.isEmpty(map.get("result_code"))) {
            tmp = map.get("result_code");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setResult_code(tmp);
        }
        if (!TextUtils.isEmpty(map.get("err_code"))) {
            tmp = map.get("err_code");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setErr_code(tmp);
        }
        if (!TextUtils.isEmpty(map.get("err_code_des"))) {
            tmp = map.get("err_code_des");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setErr_code_des(tmp);
        }
        if (!TextUtils.isEmpty(map.get("prepay_id"))) {
            tmp = map.get("prepay_id");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setPrepay_id(tmp);
        }
        if (!TextUtils.isEmpty(map.get("trade_type"))) {
            tmp = map.get("trade_type");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setTrade_type(tmp);
        }
        if (!TextUtils.isEmpty(map.get("code_url"))) {
            tmp = map.get("code_url");
            if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
                tmp = tmp.substring(8, tmp.length() - 3);
            }
            orderback.setCode_url(tmp);
        }
        return orderback;
    }

    /**
     * <p>
     * 微信支付js-sdk接口
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午1:21:22 Jack Exp $
     */
    public static Map<String, String> getWxPayData(String prePayId, String wxCode) {
        SysWxconfig ourwaySysWxconfig = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxCode, SysWxconfig.class);
        if (null == ourwaySysWxconfig) {
            return null;
        }
        Map<String, String> payMap = new HashMap<String, String>();
        String ymdhms = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = createNonceStr();
        if (nonceStr.length() > 32) {
            nonceStr = nonceStr.substring(0, 31);
        }
        payMap.put("timestamp", ymdhms);
        payMap.put("nonceStr", nonceStr);
        // System.out.println("prePayId" + prePayId);
        payMap.put("package", "prepay_id=" + prePayId);
        payMap.put("signType", "MD5");
        // 给微信支付用的信息
        SortedMap<Object, Object> params = new TreeMap<Object, Object>();
        params.put("appId", ourwaySysWxconfig.getWeAppid());
        params.put("timeStamp", ymdhms);
        params.put("nonceStr", nonceStr);
        params.put("package", "prepay_id=" + prePayId);
        params.put("signType", "MD5");
        // 进行支付签名
        String sign = createSign("UTF-8", params, ourwaySysWxconfig.getWeKey());
        payMap.put("paySign", sign);
        // System.out.println("paySign:" + sign);
        return payMap;
    }

    /**
     * <p>
     * 微信退款接口
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: WeixinUtil.java,v 0.1 2015-10-14 下午11:43:29 Jack Exp $
     */
    public static RefundBack wxtk(Map<String, String> datas, String wxCode) {
        SysWxconfig ourwaySysWxconfig = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxCode, SysWxconfig.class);
        if (null == ourwaySysWxconfig) {
            return null;
        }
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        String nonStr = DateUtil.getDateStr("yyyyMMddHHmmss");
        packageParams.put("appid", ourwaySysWxconfig.getWeAppid());// 公众账号ID
        packageParams.put("mch_id", ourwaySysWxconfig.getWeMacid());// 商户号
        packageParams.put("nonce_str", nonStr);// 随机字符串
        packageParams.put("out_trade_no", datas.get("out_trade_no"));// 商户订单号
        packageParams.put("out_refund_no", datas.get("out_refund_no"));// 商户退款单号
        packageParams.put("total_fee", datas.get("total_fee"));// 总金额
        packageParams.put("refund_fee", datas.get("refund_fee"));// 总金额
        packageParams.put("op_user_id", ourwaySysWxconfig.getWeMacid());// 操作员,操作员帐号,
        // 默认为商户号

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(ourwaySysWxconfig.getWeAppid(), ourwaySysWxconfig.getWeSecrect(), ourwaySysWxconfig.getWeKey());

        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>"
                + ourwaySysWxconfig.getWeAppid() + "</appid>"
                + "<mch_id>" + ourwaySysWxconfig.getWeMacid()
                + "</mch_id>" + "<nonce_str>" + nonStr + "</nonce_str>"
                + "<sign><![CDATA[" + sign + "]]></sign>" + "<out_trade_no>"
                + datas.get("out_trade_no") + "</out_trade_no>"
                + "<out_refund_no>" + datas.get("out_refund_no")
                + "</out_refund_no>" + "<total_fee>" + datas.get("total_fee")
                + "</total_fee>" + "<refund_fee>" + datas.get("refund_fee")
                + "</refund_fee>" + "<op_user_id>"
                + ourwaySysWxconfig.getWeMacid() + "</op_user_id>"
                + "</xml>";
        String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        try {
            String s = ClientCustomSSL.doRefund(createOrderURL, xml, wxCode);
            RefundBack back = XMLUtils.transXMLRefundBack(s);
            return back;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Map<String, Object>> getSingPLUserInfo(String wxCode, String mess) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String requestUrl = WechatConstants.WechatApiURL.URL_USERINFOBATCH.replace("ACCESS_TOKEN", accessToken.getToken());

        String result = HttpsRequest.doPost(requestUrl, mess);
        if (TextUtils.isEmpty(result)) {
            return null;
        }


        JSONObject jsonObject = JSONObject.fromObject(result);
        if (null == jsonObject) {
            return null;
        }
        JSONArray datas = jsonObject.getJSONArray("user_info_list");
        if (null == datas) {
            return null;
        }
        for (Object object : datas) {
            JSONObject obj = (JSONObject) object;
            if (null == obj) {
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("errcode", "0");
            map.put("errmsg", "");
            map.put("subscribe", obj.getString("subscribe"));
            map.put("openid", obj.getString("openid"));
            if (obj.has("nickname")) {
                map.put("nickname", obj.getString("nickname").toString());
            } else {
                map.put("nickname", "");
            }
            if (obj.has("sex")) {
                map.put("sex", obj.getString("sex").toString());
            } else {
                map.put("sex", "");
            }
            if (obj.has("city")) {
                map.put("city", obj.getString("city").toString());
            } else {
                map.put("city", "");
            }
            if (obj.has("country")) {
                map.put("country", obj.getString("country").toString());
            } else {
                map.put("country", "");
            }
            if (obj.has("province")) {
                map.put("province", obj.getString("province").toString());
            } else {
                map.put("province", "");
            }
            if (obj.has("language")) {
                map.put("language", obj.getString("language").toString());
            } else {
                map.put("language", "");
            }
            if (obj.has("headimgurl")) {
                map.put("headimgurl", obj.getString("headimgurl").toString());
            } else {
                map.put("headimgurl", "");
            }
            if (obj.has("subscribe_time")) {
                map.put("subscribe_time", obj.getString("subscribe_time")
                        .toString());
            } else {
                map.put("subscribe_time", "");
            }
            if (obj.has("remark")) {
                map.put("remark", obj.getString("remark").toString());
            } else {
                map.put("remark", "");
            }
            if (obj.has("groupid")) {
                map.put("groupid", obj.getString("groupid").toString());
            } else {
                map.put("groupid", "");
            }
            list.add(map);
        }

        return list;
    }


    /**
     * <p>方法:getCompAccessToken 获取企业号的微信accessToken </p>
     * <ul>
     * <li> @param appid TODO</li>
     * <li> @param appsecret TODO</li>
     * <li>@return AccessToken  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/10 16:51  </li>
     * </ul>
     */
    public static AccessToken getCompAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_ACCESSTOKEN.replace("APPID", appid)
                .replace("APPSECRET", appsecret);
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, "");
        // 如果请求成功
        if (null != jsonObject) {
            try {
                System.out.println("json==" + jsonObject.toString());
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                e.printStackTrace();
                System.out.println("获取企业号token失败 errcode:{} errmsg:{}");
            }
        }
        return accessToken;
    }



    /**
    *<p>方法:getWxCompMemb TODO获取企业用户，商户 </p>
    *<ul>
     *<li> @param wxCode TODO</li>
     *<li> @param code TODO</li>
    *<li>@return com.zghzbckj.userapi.wechat.model.WxUserModel  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/3/21 22:12  </li>
    *</ul>
    */
    public static WxUserModel getWxCompMemb(String wxCode, String code) {
        try {
            AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
            if (null == accessToken) {
                return null;
            }
            WxUserModel user = null;
            if (TextUtils.isEmpty(accessToken.getToken()) || TextUtils.isEmpty(code)) {
                return null;
            }
            String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_USERINFO.replace("ACCESS_TOKEN",
                    accessToken.getToken()).replace("CODE", code);
            JSONObject jsonObject = HttpsRequest.doGet(requestUrl, "");
            if (null != jsonObject) {
                try {
                    user = new WxUserModel();
                    if (jsonObject.has("UserId")) {
                        user.setUserId(jsonObject.getString("UserId"));
                    }
                    if (jsonObject.has("DeviceId")) {
                        user.setDeviceId(jsonObject.getString("DeviceId"));
                    }
                    if (jsonObject.has("OpenId")) {
                        user.setOpenId(jsonObject.getString("OpenId"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    /**
     * 企业号发送文本消息-主页型应用
     *
     * @param toUsers
     * @param toPartys
     * @param toTags
     * @param content
     * @return
     */
    public static Map<String, Object> sendWxCompTextMess(String wxCode, String toUsers,
                                                         String toPartys, String toTags, String content, String agentid) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_MESSSEND.replace("ACCESS_TOKEN",
                accessToken.getToken());
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("touser", toUsers);
        map.put("toparty", toPartys);
        map.put("totag", toTags);
        map.put("msgtype", "text");
        map.put("agentid", agentid);

        Map<String, Object> nr = new HashMap<String, Object>(1);
        nr.put("content", content);
        map.put("text", nr);
        map.put("safe", 0);
        String jsonMenu = JSONObject.fromObject(map).toString();
        // 调用接口创建菜单
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }

    /**
     * 发送图文消息，带链接跳转
     *
     * @param toUsers
     * @param toPartys
     * @param toTags
     * @param
     * @param agentid
     * @param articles
     */
    public static Map<String, Object> sendWxCompPicMess(String wxCode, String toUsers,
                                                        String toPartys, String toTags, int agentid, List<Articles> articles) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (null == articles || articles.size() <= 0) {
            return null;
        }
        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_MESSSEND.replace("ACCESS_TOKEN",
                accessToken.getToken());
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("touser", toUsers);
        map.put("toparty", toPartys);
        map.put("totag", toTags);
        map.put("msgtype", "news");
        map.put("agentid", agentid);
        Map<String, List<Articles>> content = Maps.newHashMap();
        content.put("articles", articles);
        map.put("news", content);
        String jsonMenu = JSONObject.fromObject(map).toString();
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }


    /**
     * <p>功能描述:公众号发送图文消息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return </li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2018/2/9 20:21</li>
     * </ul>
     */

    public static Map<String, Object> sendPicMessage(String wxCode, String toUsers, List<Articles> articles) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (null == articles || articles.size() <= 0) {
            return null;
        }
        String requestUrl = WechatConstants.WechatApiURL.URL_SENDPIC.replace("ACCESS_TOKEN",
                accessToken.getToken());
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("touser", toUsers);
        map.put("msgtype", "news");
        Map<String, List<Articles>> content = Maps.newHashMap();
        content.put("articles", articles);
        map.put("news", content);
        String jsonMenu = JSONObject.fromObject(map).toString();
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }

    /**
     * 企业号的userid和openid的转换
     */
    public static Map<String, Object> userId2Openid(String wxCode, String userId) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (TextUtils.isEmpty(userId)) {
            return null;
        }
        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_USER2OPENID.replace("ACCESS_TOKEN",
                accessToken.getToken());
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("userid", userId);
        map.put("agentid", "");
        String jsonMenu = JSONObject.fromObject(map).toString();
        String result = HttpsRequest.doPost(requestUrl, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }


    /**
     * 企业号成员信息
     */
    public static String QY_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    public static Map<String, Object> qyUserInfo(String wxCode, String userId) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (TextUtils.isEmpty(userId)) {
            return null;
        }
        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_USERDETAIL.replace("ACCESS_TOKEN", accessToken.getToken()).replace("USERID", userId);

        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, "");
        Map<String, Object> map = new HashMap<String, Object>(20);
        // 如果请求成功
        if (null != jsonObject) {
            if (jsonObject.has("userid")) {
                map.put("userid", jsonObject.getString("userid"));
            }
            if (jsonObject.has("name")) {
                map.put("name", jsonObject.getString("name"));
            }
            if (jsonObject.has("department")) {
                map.put("department", jsonObject.getString("department"));
            }
            if (jsonObject.has("position")) {
                map.put("position", jsonObject.getString("position"));
            }
            if (jsonObject.has("mobile")) {
                map.put("mobile", jsonObject.getString("mobile"));
            }
            if (jsonObject.has("gender")) {
                map.put("gender", jsonObject.getString("gender"));
            }
            if (jsonObject.has("email")) {
                map.put("email", jsonObject.getString("email"));
            }
            if (jsonObject.has("weixinid")) {
                map.put("weixinid", jsonObject.getString("weixinid"));
            }
            if (jsonObject.has("avatar")) {
                map.put("avatar", jsonObject.getString("avatar"));
            }
            if (jsonObject.has("status")) {
                map.put("status", jsonObject.getString("status"));
            }
            if (jsonObject.has("extattr")) {
                map.put("extattr", jsonObject.getString("extattr"));
            }
            return map;
        }
        return null;
    }


    // 获取企业号jssdk
    public static JSTickets getQyTicket(String accessToken) {
        JSTickets jsticket = null;
        String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_JSTOKEN.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsRequest.doGet(requestUrl, "");
        // 如果请求成功
        if (null != jsonObject) {
            try {
                jsticket = new JSTickets();
                jsticket.setTicket(jsonObject.getString("ticket"));
                jsticket.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                System.out.println("获取企业号的ticket失败 errcode:{} errmsg:{}"
                        + e.getMessage());
            }
        }
        return jsticket;
    }

    //
//    // 上传多媒体文件到微信服务器

    /**
     * 上传多媒体文件到微信服务器<br>
     *
     * @author qincd
     * @date Nov 6, 2014 4:11:22 PM
     */
    public static Map<String, Object> uploadMediaToWX(String filePath, String type, String wxCode) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            String url = WechatConstants.WechatApiURL.URL_UPLOADMEDIA.replace("ACCESS_TOKEN", accessToken.getToken()).replace("TYPE", type);
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();
            sb.append("--"); //必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(head);
            // 文件正文部分
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            conn.disconnect();
            return JsonUtil.jsonToMap(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从微信服务器下载多媒体文件
     *
     * @author qincd
     * @date Nov 6, 2014 4:32:12 PM
     */
    public static String downloadMediaFromWx(String wxCode, String mediaId, String fileSavePath) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return null;
        }
        if (TextUtils.isEmpty(mediaId)) {
            return null;
        }
        BufferedOutputStream bos = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        String weixinServerFileName = "";
        try {
            String requestUrl = WechatConstants.WechatApiURL.URL_DOWNLOADMEDIA.replace("ACCESS_TOKEN", accessToken.getToken()).replace("MEDIA_ID", mediaId);
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            in = conn.getInputStream();

            File dir = new File(fileSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!fileSavePath.endsWith("/")) {
                fileSavePath += "/";
            }

            String ContentDisposition = conn.getHeaderField("Content-disposition");
            weixinServerFileName = ContentDisposition.substring(
                    ContentDisposition.indexOf("filename") + 10,
                    ContentDisposition.length() - 1);
            fileSavePath += weixinServerFileName;
            bos = new BufferedOutputStream(
                    new FileOutputStream(fileSavePath));
            byte[] data = new byte[1024];
            int len = -1;

            while ((len = in.read(data)) != -1) {
                bos.write(data, 0, len);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return weixinServerFileName;
    }


    /**
     * 企业号下载临时文件
     */
    /**
     * 从微信服务器下载多媒体文件
     *
     * @author qincd
     * @date Nov 6, 2014 4:32:12 PM
     */
    public static String downloadQyMediaFromWx(String wxCode, String mediaId, String fileSavePath) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        if (null == accessToken) {
            return "";
        }
        if (TextUtils.isEmpty(mediaId)) {
            return "";
        }
        BufferedOutputStream bos = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        String weixinServerFileName = "";
        try {

            String requestUrl = WechatConstants.WechatApiURL.URL_COMPANY_MEDIA.replace("ACCESS_TOKEN",
                    accessToken.getToken()).replace("MEDIA_ID", mediaId);
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            in = conn.getInputStream();
            File dir = new File(fileSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!fileSavePath.endsWith("/")) {
                fileSavePath += "/";
            }

            String ContentDisposition = conn.getHeaderField("Content-disposition");
            weixinServerFileName = ContentDisposition.substring(
                    ContentDisposition.indexOf("filename") + 10,
                    ContentDisposition.length() - 1);
            fileSavePath += weixinServerFileName;
            bos = new BufferedOutputStream(
                    new FileOutputStream(fileSavePath));
            byte[] data = new byte[1024];
            int len = -1;

            while ((len = in.read(data)) != -1) {
                bos.write(data, 0, len);
            }
        } catch (Exception e) {

        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return weixinServerFileName;
    }


    /**
     * <p>方法:sendMiniProgrammTemplateMsg 发送小程序消息模板 </p>
     * <ul>
     * <li> @param map TODO</li>
     * <li> @param wxCode TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/5/14 22:57  </li>
     * </ul>
     */
    public static Map<String, Object> sendMiniProgrammTemplateMsg(Map<String, Object> map, String wxCode) {
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        String url = WechatConstants.WechatApiURL.URL_MINIPROGRAMM_TEMPLATEMESSAGE.replace("ACCESS_TOKEN", accessToken.getToken());
        String jsonMenu = JSONObject.fromObject(map).toString();
        String result = HttpsRequest.doPost(url, jsonMenu);
        return JsonUtil.jsonToMap(result);
    }

    /**
    *<p>方法:createMember TODO创建企业用户 </p>
    *<ul>
     *<li> @param wxCode TODO</li>
     *<li> @param user TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/3/21 16:12  </li>
    *</ul>
    */
    public static boolean createMember(String wxCode,CreateMember user){
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        String url = WechatConstants.WechatApiURL.URL_COMP_CREATEUSER.replace("ACCESS_TOKEN", accessToken.getToken());
        String jsonMenu = JSONObject.fromObject(user).toString();
        String result = HttpsRequest.doPost(url, jsonMenu);
        Map map=JsonUtil.jsonToMap(result);
        if(null!=map&&Integer.valueOf(map.get("errcode").toString())==0){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    /**
    *<p>方法:inviteUser TODO邀请用户 </p>
    *<ul>
     *<li> @param wxCode TODO</li>
     *<li> @param userIds TODO</li>
     *<li> @param toWho TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/3/21 22:18  </li>
    *</ul>
    */
    public static boolean inviteUser(String wxCode,List<String> userIds,String toWho){
        AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxCode, AccessToken.class);
        String url = WechatConstants.WechatApiURL.URL_COMP_INVITEEUSER.replace("ACCESS_TOKEN", accessToken.getToken());
        Map<String,List>users=Maps.newHashMap();
        users.put(toWho,userIds);
        String jsonMenu = JSONObject.fromObject(users).toString();
        String result = HttpsRequest.doPost(url, jsonMenu);
        Map map=JsonUtil.jsonToMap(result);
        if(null!=map&&Integer.valueOf(map.get("errcode").toString())==0){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }


    public static String send_temp_msg_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public static TemplateMsg sendTemplateMsgWithCode(Map map, String accessToken) {
        TemplateMsg msg = new TemplateMsg();
        String url = send_temp_msg_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(map).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (jsonObject.has("errcode")) {
                msg.setErrcode(jsonObject.getInt("errcode") + "");
            }
            if (jsonObject.has("errmsg")) {
                msg.setErrcode(jsonObject.getString("errmsg"));
            }
            if (jsonObject.has("msgid")) {
                msg.setMsgid(jsonObject.getLong("msgid") + "");
            }
        }
        return msg;
    }



    public static void main(String[] args) {


        try {
            AccessToken token = WeixinUtils.getCompAccessToken("ww559581a92b011a79", "FfcNdQUIHcaBg4bwXLn0ih0qb5AWG9fgMxI1qxmIJt8");


            List<Integer> alasd= Lists.newArrayList();
            alasd.add(2);
            alasd.add(1);
            String url = WechatConstants.WechatApiURL.URL_COMP_INVITEEUSER.replace("ACCESS_TOKEN", token.getToken());
            Map<String,List>users=Maps.newHashMap();
            users.put("party",alasd);
            String jsonMenu = JSONObject.fromObject(users).toString();
            String result = HttpsRequest.doPost(url, jsonMenu);
            System.out.println(result);
//            Map dsdsd=new HashMap();
//            dsdsd.put("userid","gender001");
//            dsdsd.put("mobile","18667508695");
//            dsdsd.put("department",alasd);
//            dsdsd.put("name","陈嘎嘎");
//            dsdsd.put("is_leader_in_dept",alas2d);
//            createMembers(accessToken,createMember);
//            String jsonStr = "{\"first\":{\"value\":\"亲，宝贝已经启程了，好想快点来到你身边\"},\"delivername\":{\"value\":\"顺丰快递\"},\"ordername\":{\"value\":\"韩版修身中长款风衣外套\"},\"remark\":{\"DATA\":\"如果疑问，请在微信服务号中输入“KF”，**将在第一时间为您服务！\"}}";
//            String jsonStr2 = "{ \t\"remark\": { \t\t\"color\": \"#173177\", \t\t\"value\": \"尊敬的纳税人，正在呼号:A0026(),请立即前往指定窗口办理业务\" \t}, \t\"keyword1\": { \t\t\"color\": \"#173177\", \t\t\"value\": \"F08号窗口\" \t}, \t\"keyword2\": { \t\t\"color\": \"#173177\", \t\t\"value\": \"2018-01-19 09:36:12\" \t}, \t\"first\": { \t\t\"color\": \"#173177\", \t\t\"value\": \"超过指定时间将可能被过号\" \t} }\n";
//            Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String, Object>>();
//            try {
//                Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
//                Type type = new TypeToken<Map<String, Map<String, Object>>>() {
//                }.getType();
//                dataMap = gson2.fromJson(jsonStr2, type);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            Map<String, Object> bigMap = new HashMap<String, Object>();
//            Map<String, Object> map1 = new HashMap<String, Object>();
//            map1.put("value", "亲，宝贝已经启程了，好想快点来到你身边");
//            bigMap.put("first", map1);
//            map1 = new HashMap<String, Object>();
//            map1.put("value", "顺丰快递");
//            bigMap.put("delivername", map1);
//            map1 = new HashMap<String, Object>();
//            map1.put("value", "韩版修身中长款风衣外套");
//            bigMap.put("ordername", map1);
//            map1 = new HashMap<String, Object>();
//            map1.put("value", "如果疑问，请在微信服务号中输入");
//            map1.put("color", "#cccccc");
//            bigMap.put("remark", map1);

//            WeixinUtils.sendTemplateMsgWithAccessToken("o0hDfvqzQQg2J3Pd2LE9-R9qewp8", "W9EThAukKlp5IzztWFyNKhCppJReZq7gQYJNigngqTQ", "#ff0000", dataMap, accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }




        // String openId = WeixinUtil.userId2Openid(token.getToken(), "zhou");
//        Articles art = new Articles();
//        art.setTitle("消息中心测试");
//        art.setDescription("您有新的工作未处理");
//        art.setPicurl("http://iteeio.zust.edu.cn/zybx/jsp/images/gsjj_banner.jpg");
//        art.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4c18a056ebf26d95&redirect_uri=http%3A%2F%2Fiteeio.zust.edu.cn%2Fzybx%2FwxCompIndex!shouyeGsgl.htm&response_type=code&scope=SCOPE&state=wx02#wechat_redirect");

//        List<Articles> ats = new ArrayList<Articles>();
//        ats.add(art);

//        WeixinUtils.sendWxCompPicMess(token.getToken(), "zhou", "", "", 4, ats);
        // Map<String, String> map = WeixinUtil.qyUserInfo(token.getToken(),
        // "zhou");
        // Set set = map.keySet();
        // for (Object object : set) {
        // System.out.println(object + "=" + map.get(object.toString()));
        // }

        // AccessToken keys = getCompAccessToken("wx4c18a      056ebf26d95",
        // "Ly5JTkfFV2EwlrRryxwwxagFueRWOk7xI-JWZjgOhQ6MmYTNYAQl99xg67e14_qD");
        // System.out.println(keys.getToken());
        // sendWxCompTextMess(keys.getToken(), "zhou", "", "",
        // "你有新的保险投保咨询，请进入应用查看", "5");

        // Map<String, String> datas = new HashMap<String, String>();
        // datas.put("out_trade_no", "B4F9B07695F04765B918C390A3D5B205");
        // datas.put("out_refund_no", "123123");
        // datas.put("total_fee", "1");
        // datas.put("refund_fee", "1");
        // WeixinUtil.wxtk(datas);
        // Date d
        // Date d = new Date();

        // System.out.println(WeixinUtil.transferLongToDate("yyyy-MM-dd HH:mm:ss",
        // new Long(1434093047)));
        // String appid = "wx5c3a1a91cf6d93c3";
        // String appSecrect = "c578656d53b4f16d121e032a05175f53";
        // String token = WeixinUtil.getAccessStringToken(appid, appSecrect);
        // Map<String, Object> map = WeixinUtil.getUser(token, "");
        // List list = (List) map.get("data");
        // for (Object object : list) {
        // Map<String, Object> _map = WeixinUtil.getSingUserInfo(token,
        // object.toString());
        // System.out.println(_map.get("nickname"));
        // System.out.println(_map.get("headimgurl"));
        // }

        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("touser", "oS7wGswsk1QVKyrygX0bCtP07H90");
        // map.put("template_id",
        // "1d1rilsq-EtQSZhiz5cP_WojsickAOU9TWgFfUfohIQ");
        // map.put("url", "http://www.163.com");
        // map.put("topcolor", "#FF0000");
        // Map<String,Map<String,String>> valMap = new
        // HashMap<String,Map<String,String>>();
        // Map<String,String> firstMap = new HashMap<String,String>();
        // firstMap.put("value", "代办任务提醒");
        // firstMap.put("color", "#173177");
        // valMap.put("first", firstMap);
        //
        // Map<String,String> keyword1 = new HashMap<String,String>();
        // keyword1.put("value", "代办任务提醒1");
        // keyword1.put("color", "#173177");
        // valMap.put("keyword1", keyword1);
        // Map<String,String> keyword2 = new HashMap<String,String>();
        // keyword2.put("value", "代办任务提醒2");
        // keyword2.put("color", "#173177");
        // valMap.put("keyword2", keyword2);
        // Map<String,String> remark = new HashMap<String,String>();
        // keyword2.put("value", "代办任务提醒sd1");
        // keyword2.put("color", "#173177");
        // valMap.put("remark", remark);
        // map.put("data",valMap);
        // TemplateMsg msg = WeixinUtil.sendTemplateMsg(map, token);
        // System.out.println(msg.getErrmsg());
        //
        // String s =
        // "{\"remark\":{\"color\":\"#173177\",\"value\":\"代办任务提醒1\"},\"keyword1\":{\"color\":\"#173177\",\"value\":\"代办任务提醒1\"},\"keyword2\":{\"color\":\"#173177\",\"value\":\"代办任务提醒sd1\"},\"first\":{\"color\":\"#173177\",\"value\":\"代办任务提醒\"}}";
        // JSONObject json = JSONObject.fromObject(s);
        // Map<String, MorphDynaBean> map = (Map<String, MorphDynaBean>)
        // json.toBean(json, Map.class);
        // Set keys = map.keySet();
        // for (Object object : keys) {
        // System.out.println(object.toString());
        // MorphDynaBean _map = map.get(object.toString());
        // System.out.println(_map.get("color"));
        // System.out.println(_map.get("value"));
    }
//
//    }
}