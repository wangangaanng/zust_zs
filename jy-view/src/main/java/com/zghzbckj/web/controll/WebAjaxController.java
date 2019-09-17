package com.zghzbckj.web.controll;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.web.constant.Constant;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>方法 WebAjaxController : <p>
 * <p>说明:统一对外接口类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 22:30
 * </pre>
 */
@Controller
@RequestMapping(value = "/webAjax")
public class WebAjaxController {

    private static final Logger log = Logger.getLogger(WebAjaxController.class);

    private ResponseMessage responseMessage;

    @RequestMapping(value = "executeAPI", method = RequestMethod.POST)//原本是POST
    @ResponseBody
    @CrossOrigin("*")
    public String executeApi(HttpServletRequest request, PublicData data) {
        /*发起请求*/
        String result = "";
        ResponseMessage mess = null;
        try {
            result = UnionHttpUtils.doPost(data);
            if (TextUtils.isEmpty(result)) {
                return JsonUtil.toJson(ResponseMessage.setMessage(Constant.FAIL, Constant.ERROR_FAIL, null));
            }
            log.info(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            mess = ResponseMessage.setMessage(Constant.FAIL, e.getMessage(), result);
        }
        return JsonUtil.toJson(mess);
    }



    @RequestMapping(value = "execItemApi", method = RequestMethod.POST)//原本是POST
    @ResponseBody
    @CrossOrigin("*")
    public String execItemApi(HttpServletRequest request, PublicData data) {
        /*发起请求*/
        String result = null;
        ResponseMessage mess = null;
        try {
            Map map=JsonUtil.jsonToMap(data.getData());
            result=UnionHttpUtils.doPost(Constant.URL_HOST+data.getMethod(),map,"utf-8");
//            result=HttpClientUtils.ajaxPostJson(Constant.URL_HOST+data.getMethod(),data.getData());
            if (TextUtils.isEmpty(result)) {
                return JsonUtil.toJson(ResponseMessage.setMessage(Constant.FAIL, Constant.ERROR_FAIL, null));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            mess = ResponseMessage.setMessage(Constant.FAIL, e.getMessage(), result);
        }
        return JsonUtil.toJson(mess);
    }


//    @RequestMapping(value = "/getUserId", method = RequestMethod.POST)
//    @ResponseBody
//    public String newest(HttpServletRequest request) {
//        String code = request.getParameter("code");
//        String wxid = request.getParameter("wxid");
//        HttpSession session = request.getSession();
//        WxUserModel user = null;
//        user = (WxUserModel) session.getAttribute(CommonConstant.WX_COMP_USER_SESSION);
//        if (null != user) {
//            return JsonUtil.toJson(user);
//        }
//        log.info(code + "================================================" + wxid);
//        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(wxid)) {
//            return "error";
//        }
//        user = WeixinUtils.getWxCompMemb(wxid, code);
//        if(null!=user){
//            session.setAttribute(CommonConstant.WX_COMP_USER_SESSION,user);
//            return JsonUtil.toJson(user);
//        }
//        return "error";
//
//    }


//    @ResponseBody
//    @RequestMapping(value = "signWx", method = RequestMethod.POST)//原本是POST
//    public String signWx(HttpServletRequest request) {
//        responseMessage = new ResponseMessage();
//        String url = request.getParameter("urlStr");
//        String wxid = request.getParameter("wxid");
//        log.info("wxid" + wxid);
//        JSTickets jsTickets = (JSTickets) CommonConstant.weixin_map.get(WechatConstants.WECHAT_JS_REDIS_PREX + wxid);
//        Map<String, String> map = JSSDKSign.sign(jsTickets.getTicket(), url);
//        SysWxconfig sysWxconfig = (SysWxconfig) CommonConstant.weixin_map.get(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxid);
//        map.put("appId", sysWxconfig.getWeAppid());
//        responseMessage.setBackCode(0);
//        responseMessage.setBean(map);
//        return JsonUtil.toJson(responseMessage);
//    }

}
