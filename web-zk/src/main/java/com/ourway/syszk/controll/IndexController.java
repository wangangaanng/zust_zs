package com.ourway.syszk.controll;

import com.beust.jcommander.internal.Maps;
import com.ourway.WebZKConstant;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.base.zk.zpspring.SpringZkBaseControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            String tpPath = ZKConstants.SYSTEM_FILE_URL;
            String url = WebZKConstant.WX_VIEW_URL;
            String picName = TextUtils.getUUID() + ".jpg";
            //生成二维码
            QRCodeUtil.encode(url, "/mnt/files/zjcFiles/qrcode/zust.png", ZKConstants.SYSTEM_FILE_PATH + "/qrcode/",
                    picName, true);
            Map map = Maps.newHashMap();

            map.put("qrPic", tpPath + "qrcode/" + picName);
            map.put("name", name);
            map.put("zphJbdd", zphJbdd);
            map.put("zphKsrq", zphKsrq);
            map.put("zphJtsj", zphJtsj);
//            map.put("name", new String(name.getBytes("utf-8"),"ISO-8859-1"));
//            map.put("zphJbdd", new String(zphJbdd.getBytes("utf-8"),"ISO-8859-1"));
//            map.put("zphKsrq", new String(zphKsrq.getBytes("utf-8"),"ISO-8859-1"));
//            map.put("zphJtsj",new String(zphJtsj.getBytes("utf-8"),"ISO-8859-1"));
            request.setAttribute("map", map);
//            request.setAttribute("qrCode", tpPath+ picName);
            return "projectReport";
//            return "redirect:/projectReport.htm?name=" + name + "&qrCode=" + tpPath + "qrcode/" + picName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/error.htm";
//        return "redirect:/index.htm";

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
