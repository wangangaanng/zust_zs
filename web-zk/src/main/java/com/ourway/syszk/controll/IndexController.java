package com.ourway.syszk.controll;

import com.beust.jcommander.internal.Maps;
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
                return "redirect:/index.htm?errorMess=1";
            } else {
                //调用成功
                return "redirect:/login.do?jsessionId=" + employVO.getEmpMobileCode();
            }
        }
        return "redirect:/index.htm?errorMess=1";

    }
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
