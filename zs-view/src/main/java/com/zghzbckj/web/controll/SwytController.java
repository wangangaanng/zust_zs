package com.zghzbckj.web.controll;

import com.google.common.collect.Maps;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>说明:三位一体招生网</p>
 * <pre>
 * @author xiayuwei
 * @date 2019/11/4 20:54
 * </pre>
 */
@Controller
public class SwytController {
    private static final Logger log = Logger.getLogger(SwytController.class);
    @ModelAttribute
    public void setConfig(Model model) {
        model.addAttribute("imagePath", ApiConstants.imagePath);
        model.addAttribute("localUrl", ApiConstants.localUrl);
        model.addAttribute("  ", ApiConstants.uploadUrl);
    }
   // 三位一体框首页容器
   @RequestMapping(value = "/trinityEnrollment/{pageType}", method = RequestMethod.GET)
   public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String pageType,@CookieValue(value = "swOwid",required = false) String swOwid) throws UnsupportedEncodingException {
        //pageType跳转到的页面
       view.addObject("page",pageType);
        swOwid = "1b47f10b042a4f2b877a47d107fda132";
       //判断是否有个人owid 没有说明没登陆
       if(StringUtils.isEmpty(swOwid)){
           view.setViewName("SWlogin");
       }else{
           //获取学校信息
           Map param = Maps.newHashMap();
           param.put("yhRefOwid",swOwid);
           param.put("pageNo", "0");
           param.put("pageSize", "20");
           ResponseMessage resultMess  = new ResponseMessage();
           PublicData _data = UnionHttpUtils.manageParam(param, "zustswyt/bckjBizXxpz/getXxxx");
           resultMess = UnionHttpUtils.doPosts(_data);
           if(!StringUtils.isEmpty(resultMess.getBean())) {
               Map<String, Object> records = (Map<String, Object>) resultMess.getBean();
               //单条学院数据
               List<Map<String, Object>> List = (List<Map<String, Object>>) records.get("list");
               //申请表owid 未申请没有改字段
               if(!StringUtils.isEmpty(List.get(0).get("applyOwid"))){
                   view.addObject("applyOwid",List.get(0).get("applyOwid"));
                   //报名进行到的状态
                   view.addObject("processState",List.get(0).get("bmState"));
               }else{
                   view.addObject("applyOwid","");
               }
           }

           //s

           //去首页：总容器页面
           view.setViewName("trinityEnrollment");
       }
       return view;
   }
    @RequestMapping(value = "SWlogin", method = RequestMethod.GET)
    public ModelAndView SWlogin(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWlogin");
        return view;
    }

    @RequestMapping(value = "SWregistered", method = RequestMethod.GET)
    public ModelAndView SWregistered(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWregistered");
        return view;
    }

    @RequestMapping(value = "SWpassword", method = RequestMethod.GET)
    public ModelAndView SWYTpassword(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWpassword");
        return view;
    }
}
