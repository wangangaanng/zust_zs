package com.ourway.syszk.controll;

import com.beust.jcommander.internal.Maps;
import com.ourway.WebZKConstant;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.CookieUtils;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.base.zk.zpspring.SpringZkBaseControl;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class IndexController extends SpringZkBaseControl {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        //把常用的参数设置到界面上，并传递给界面使用
        System.out.println("client ip :" + request.getRemoteAddr() + " : " + DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
        return "index";
    }

    //    @RequestMapping("/projectReport")
//    public String projectReport(HttpServletRequest request) {
//        CookieUtils.setRequest(request);
//        //把常用的参数设置到界面上，并传递给界面使用
//        System.out.println("client ip :" + request.getRemoteAddr() + " : " + DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
//        return "projectReport";
//    }
    @RequestMapping("/leaderIndex")
    public String leaderIndex(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        return "leaderIndex";
    }


    @RequestMapping("/swytIndex")
    public String swytIndex(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        return "swytIndex";
    }

    @RequestMapping("zswIndex")
    public String zswIndex(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        return "zswIndex";
    }

    @RequestMapping("/loginNewDetail")
    public String newsDetail(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        String type = request.getParameter("type");
        String conOwid = request.getParameter("owid");
        request.setAttribute("type", type);
        request.setAttribute("conOwid", conOwid);
        return "loginNewDetail";
    }

    /**
     * 包头市政府采购网底部统计图
     */
    @RequestMapping("/govChartIndex")
    public String govChartIndex(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        return "govChartIndex";
    }

    @RequestMapping("/forgetPsw")
    public String forgetPsw(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        return "forgetPsw";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        //传递request，主要用于当html跳转到zk的时候，session处理问题
        CookieUtils.setRequest(request);
        logger.info(request.getParameter("empId"));
        logger.info(request.getParameter("empPsw"));
        String empId = request.getParameter("empId");
        String empPsw = request.getParameter("empPsw");

        if (!com.ourway.base.utils.TextUtils.isEmpty(empId) && !com.ourway.base.utils.TextUtils.isEmpty(empPsw)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("empNo", empId.trim());
            params.put("empPsw", empPsw.trim());
            params.put("language", "chn");
            EmployVO employVO = LoginUtil.loginWithRandCode(params);
            if (null == employVO) {
                return "redirect:/index.htm?error";
            } else {
                //调用成功
                return "redirect:/login.do?jsessionId=" + employVO.getEmpMobileCode();
            }
        }
        return "redirect:/index.htm?error";

    }


    @RequestMapping(value = "/report")
    public String report(HttpServletRequest request) {
        //传递request，主要用于当html跳转到zk的时候，session处理问题
        CookieUtils.setRequest(request);
        try {
            String name = request.getParameter("name");
            String zphJbdd = request.getParameter("zphJbdd");
            String zphKsrq = request.getParameter("zphKsrq");
            String zphJtsj = request.getParameter("zphJtsj");
            String qdsj1 = request.getParameter("qdsj1");
            String qdsj2 = request.getParameter("qdsj2");
            String owid = request.getParameter("owid");
            String token = request.getParameter("token");
            System.out.println("token:" + token);
            String tpPath = ZKConstants.SYSTEM_FILE_URL;
            String url = WebZKConstant.WX_VIEW_URL;
            String picName = DateUtil.getTimeStamp() + ".png";
            System.out.println("开始生成二维码");
            //生成二维码
//            QRCodeUtil.encode(url, "/mnt/files/zjcFiles/qrcode/zust.png", ZKConstants.SYSTEM_FILE_PATH + "/qrcode/",
//                    picName, true);

//            String tokenStr = CacheUtil.getVal("config.wechat.wx618803b392a8c474");
//            System.out.println("token:" + tokenStr);
//            String timeStamp = DateUtil.getTimeStamp();
            String timeStamp = owid.substring(0, 14);
            getminiqrQr(timeStamp, token, picName);
            System.out.println("owid" + timeStamp);


            Map map = Maps.newHashMap();
            map.put("qrPic", tpPath + "qrcode/" + picName);
            if (!TextUtils.isEmpty(name)) {
                map.put("name", name);
            } else {
                map.put("name", "待定");
            }
            if (!TextUtils.isEmpty(qdsj1)) {
                map.put("qdsj1", qdsj1);
            } else {
                map.put("qdsj1", "待定");
            }
            if (!TextUtils.isEmpty(qdsj2)) {
                map.put("qdsj2", qdsj2);
            } else {
                map.put("qdsj2", "待定");
            }
            if (!TextUtils.isEmpty(zphJbdd)) {
                map.put("zphJbdd", zphJbdd);
            } else {
                map.put("zphJbdd", "待定");
            }
            if (!TextUtils.isEmpty(zphKsrq)) {
                map.put("zphKsrq", zphKsrq);
            } else {
                map.put("zphKsrq", "待定");
            }
            if (!TextUtils.isEmpty(zphJtsj)) {
                map.put("zphJtsj", zphJtsj);
            } else {
                map.put("zphJtsj", "待定");
            }
            request.setAttribute("map", map);
            return "projectReport";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/error.htm";

    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getTimeStamp());
    }

    public Map getminiqrQr(String sceneStr, String accessToken, String picName) {
        System.out.println("开始小程序二维码生成");
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/qiandao/qiandao");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            System.out.println("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            System.out.println(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);
            File file = new File(ZKConstants.SYSTEM_FILE_PATH + "/qrcode/" + picName);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久小程序码URL接口异常");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
//    public static void main(String[] args) {
//        try {
//            QRCodeUtil.encode("www.baidu.com",  "C:\\files\\kk.png", "C:\\files\\qrcode\\",
//                    "a.jpg", true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    Map<String, Object> getWxLogin(String sessionId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>(2);
            params.put("appId", ZKConstants.EXT_CONSTANTS.get("appId"));
            params.put("state", ZKConstants.EXT_CONSTANTS.get("state"));
            PublicData data = PublicData.instantceWithNoLogin();
            String url = ZKConstants.EXT_CONSTANTS.get("qrCodeUrl").toString();
            data.setData(JsonUtil.toJson(params));
            data.setSessionId(sessionId);
            String result = HttpUtils.doPost(null, url, JsonUtil.jsonToMap(JsonUtil.toJson(data)), BaseConstants.UTF8, false, "");
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0) {
                String loginUrl = mess.getBean().toString();
                params.clear();
                params.put("loginUrl", loginUrl);
                return params;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(HttpServletRequest request) {
        CookieUtils.setRequest(request);
        try {
            String scence = request.getParameter("scence");
            String cityId = request.getParameter("cityId");
            request.setAttribute("cityId", scence);
            EmployVO employVO = LoginUtil.loginWithScanCode(scence);
            if (null == employVO) {
                return "allIndex";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("employ", employVO);
                session.setAttribute("cityId", cityId);
                return "redirect:/login.do?" + System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/allIndex";
    }


}
