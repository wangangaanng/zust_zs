package com.zghzbckj.manage.utils;


import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.manage.entity.SysWxconfig;
import com.zghzbckj.util.TextUtils;
import com.zghzbckj.wechat.WechatConstants;
import com.zghzbckj.wechat.model.WxXcxUserModel;
import com.zghzbckj.wechat.utils.AesCbcUtil;
import com.zghzbckj.wechat.utils.MyX509TrustManager;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SmallAppUtil {
    private static final Logger log = LoggerFactory.getLogger(SmallAppUtil.class);
    public static String openid_url="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";



    public static WxXcxUserModel getOpenId (Map<String,Object> dataMap) {
        String code= dataMap.get("code").toString();
        String wxid = dataMap.get("wxid").toString();
        String iv = dataMap.get("iv").toString();
        String encryptedData = dataMap.get("encryptedData").toString();
        log.info(code + "================================================");
        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(wxid)) {
            return null;
        }
        if (TextUtils.isEmpty(iv) || TextUtils.isEmpty(encryptedData)) {
            return null;
        }
        SysWxconfig wxconfig = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxid, SysWxconfig.class);
        Map result = SmallAppUtil.getOpenId(wxconfig.getWeAppid(), wxconfig.getWeSecrect(), code);
        if (null != result) {
            String session_key = result.get("session_key").toString();
            log.info(" session_key---" + session_key);
            try {
                String results = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
                log.info(" results---" + results);
                WxXcxUserModel userModel = SmallAppUtil.getWxXcxUser(results);
                return userModel;
            } catch (Exception e) {
                log.error("获取unionid解密失败{}", e);
                return null;
            }
        } else {
            return null;
        }
    }
    public static Map<String,Object> getOpenId(String appId, String secret, String js_code){
        String url =openid_url.replace("APPID",appId).replace("SECRET",secret).replace("JSCODE",js_code);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        if(null==jsonObject.get("errcode")){
            Map<String,Object> param = new HashMap<>();
            if(jsonObject.has("openid")){
                param.put("openId",jsonObject.get("openid").toString());
            }
            if(jsonObject.has("session_key")){
                param.put("session_key",jsonObject.get("session_key").toString());
            }
            if(jsonObject.has("unionid")){
                param.put("unionid",jsonObject.get("unionid").toString());
            }
            return param;
        }else{
            log.info(jsonObject.toString()+jsonObject.get("errcode"));
        }
        return null;
    }




    /*解析数据*/
    public static WxXcxUserModel getWxXcxUser(String result){
        WxXcxUserModel userModel = new WxXcxUserModel();
        JSONObject userInfoJSON = JSONObject.fromObject(result);
        if (TextUtils.isEmpty(userInfoJSON)) {
            return userModel;
        }
        userModel.setOpenId(userInfoJSON.getString("openId"));
        if(!TextUtils.isEmpty(userInfoJSON.getString("unionId"))) {
            userModel.setUnionid(userInfoJSON.getString("unionId"));
        }
        userModel.setAvatarUrl(userInfoJSON.getString("avatarUrl"));
        userModel.setCity(userInfoJSON.getString("city"));
        userModel.setProvince(userInfoJSON.getString("province"));
        userModel.setGender(userInfoJSON.getString("gender"));
        userModel.setNickname(userInfoJSON.getString("nickName"));

        return userModel;
    }

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

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

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
}
