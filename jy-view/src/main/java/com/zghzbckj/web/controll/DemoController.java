package com.zghzbckj.web.controll;

import com.google.common.collect.Maps;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.web.constant.Constant;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.PropertiesUtil;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>方法 DemoController : <p>
 * <p>说明:Spring Mvc 测试类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 20:54
 * </pre>
 */
@Controller
public class DemoController {
    private static final Logger log = Logger.getLogger(DemoController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,ModelAndView view) {
        view.setViewName("index");
        Map param=Maps.newHashMap();
        param.put("wzbh","1");
        param.put("fid","-1");
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjDicMenu/getLmMenu");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        view.addObject("bean",result.getBean());
        return view;
    }

    @RequestMapping(value = "aboutUs", method = RequestMethod.GET)
    public String aboutUs(HttpServletRequest request) {
        return "aboutUs";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request) {
        return "detail";
    }

    @RequestMapping(value = "detail2/{path}", method = RequestMethod.GET)
    public String detail2(HttpServletRequest request, @PathVariable String path) {

        return "detail2";
    }


    @RequestMapping(value = "askList", method = RequestMethod.GET)
    public ModelAndView askList(HttpServletRequest request,ModelAndView view) {
        view.setViewName("askList");
        return view;
    }

    @RequestMapping(value = "contactUs", method = RequestMethod.GET)
    public ModelAndView contactUs(HttpServletRequest request,ModelAndView view) {
        view.setViewName("contactUs");
        return view;
    }

    @RequestMapping(value = "enterpriseGuide", method = RequestMethod.GET)
    public ModelAndView enterpriseGuide(HttpServletRequest request,ModelAndView view) {
        view.setViewName("enterpriseGuide");
        return view;
    }

    @RequestMapping(value = "enterpriseReg", method = RequestMethod.GET)
    public ModelAndView enterpriseReg(HttpServletRequest request,ModelAndView view) {
        view.setViewName("enterpriseReg");
        Map param=Maps.newHashMap();
        param.put("type","20000");
        PublicData publicData= UnionHttpUtils.manageParam(param,"webApi/dicValue/getValueByDic.do");
        ResponseMessage qyGsxz  = UnionHttpUtils.doPosts(publicData);
        view.addObject("qyGsxz",qyGsxz.getBean());
        return view;
    }

    @RequestMapping(value = "enterpriseService", method = RequestMethod.GET)
    public ModelAndView enterpriseService(HttpServletRequest request,ModelAndView view) {
        view.setViewName("enterpriseService");
        return view;
    }

    @RequestMapping(value = "jobFair", method = RequestMethod.GET)
    public ModelAndView jobFair(HttpServletRequest request,ModelAndView view) {
        view.setViewName("jobFair");
        return view;
    }

    @RequestMapping(value = "newsDetail", method = RequestMethod.GET)
    public ModelAndView newsDetail(HttpServletRequest request,ModelAndView view) {
        view.setViewName("newsDetail");
        return view;
    }

    @RequestMapping(value = "newsList", method = RequestMethod.GET)
    public ModelAndView newsList(HttpServletRequest request,ModelAndView view) {
        view.setViewName("newsList");
        return view;
    }

    @RequestMapping(value = "studentService", method = RequestMethod.GET)
    public ModelAndView studentService(HttpServletRequest request,ModelAndView view) {
        view.setViewName("studentService");
        return view;
    }

    @RequestMapping(value = "teacherDetail", method = RequestMethod.GET)
    public ModelAndView teacherDetail(HttpServletRequest request,ModelAndView view) {
        view.setViewName("teacherDetail");
        return view;
    }



//    @RequestMapping(value = "table1", method = RequestMethod.GET)
//    public String table1(HttpServletRequest request) {
//        return "table1";
//    }

    @RequestMapping(value = "table", method = RequestMethod.GET)
    public String table(HttpServletRequest request) {
        return "table";
    }

//    @RequestMapping(value = "table2", method = RequestMethod.GET)
//    public String table2(HttpServletRequest request) {
//        return "table2";
//    }

    @RequestMapping(value = "print1", method = RequestMethod.GET)
    public ModelAndView print1(HttpServletRequest request) {
        String owid=request.getParameter("owid");
        ModelAndView mv = new ModelAndView();
        if(TextUtils.isEmpty(owid)){
            mv.addObject("errorMess", "参数为空，请重试");
            mv.setViewName("error");
            return mv;
        }
        PublicData data =new PublicData();
        data.setMethod("/web/compItem/getByOwid");
        Map mapPram= Maps.newHashMap();
        mapPram.put("owid",owid);
        initProperty();
        data.setData(JsonUtil.toJson(mapPram));
        /*发起请求*/
        String result = "";
        ResponseMessage mess = null;
        try {
            result = UnionHttpUtils.doPost(data);
            Map resData=JsonUtil.jsonToMap(result);
            Map bean= (Map) resData.get("bean");
            List<Map> gdList= (List<Map>) bean.get("gdList");
            mv.addObject("listSize", gdList.size()+1);
            mv.addObject("dataBean", resData.get("bean"));
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("errorMess", "系统繁忙，请重试");
            mv.setViewName("error");
            return mv;
        }
        mv.setViewName("print1");
        return mv;
    }

    private void initProperty() {
        if (TextUtils.isEmpty(Constant.APP_KEY) || TextUtils.isEmpty(Constant.URL_HOST)) {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
            Properties p = propertiesUtil.loadProperties("config.properties");
            Constant.APP_KEY = PropertiesUtil.readProperty(p, Constant.CLIENT_KEY);
            Constant.URL_HOST = PropertiesUtil.readProperty(p, Constant.HOST_KEY);
            Constant.APP_SECTRECT = PropertiesUtil.readProperty(p, Constant.SECRECT_KEY);
        }
    }

    private void handelRequest(HttpServletRequest request) {
        try {
            Enumeration<String> vns = request.getParameterNames();
            String name = "";
            while (vns.hasMoreElements()) {
                name = vns.nextElement();
                request.setAttribute(name, request.getParameter(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>功能描述：accessUserInfo 获取用户的基本信息放在缓存中</p>
     * <ul>
     * <li>@param [request, session]</li>
     * <li>@return void</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-3-30 下午4:52</li>
     * </ul>
     */
//    private void accessUserInfo(HttpServletRequest request, HttpSession session) {
//        try {
//            WxUserModel wxUser = null;
//            /*排除被分享的用户数据库中还是subscribe=0的情况导致获取不到微信头像等数据*/
//            if (session.getAttribute("wxUser") != null && ((WxUserModel) session.getAttribute("wxUser")).getSubscribe() != null &&
//                    ((WxUserModel) session.getAttribute("wxUser")).getSubscribe() != 0) {
//                wxUser = (WxUserModel) session.getAttribute("wxUser");
//            } else {
//                handelRequest(request);
//
//                String code = request.getParameter("code");//微信静默方式获取用户授权的code，5分钟内有效
//                String state = request.getParameter("state");//可以作为微信的编号
//                System.out.println(code + "====" + state);
//                wxUser = new WxUserModel();
//                if (TextUtils.isEmpty(code))
//                    throw new Exception();
//                wxUser = WeixinUtils.getWxCompMemb(code, state);
//            }
//            if (null == wxUser || TextUtils.isEmpty(wxUser.getOpenId()))
//                throw new Exception();
//            session.setAttribute("wxUser", wxUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




}
