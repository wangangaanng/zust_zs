package com.zghzbckj.web.controll;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * <p>方法 DemoController : <p>
 * <p>说明:Spring Mvc 测试类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 20:54
 * </pre>
 */
@Controller
public class SwytController {
    private static final Logger log = Logger.getLogger(SwytController.class);
    @ModelAttribute
    public void setConfig(Model model) {
        model.addAttribute("imagePath", ApiConstants.imagePath);
        model.addAttribute("localUrl", ApiConstants.localUrl);
        model.addAttribute("uploadUrl", ApiConstants.uploadUrl);
    }
   // 三位一体框首页容器
   @RequestMapping(value = "/trinityEnrollment/{pageType}", method = RequestMethod.GET)
   public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String pageType) throws UnsupportedEncodingException {
       view.addObject("page",pageType);
       view.setViewName("trinityEnrollment");
       return view;
   }
    @RequestMapping(value = "SWYTlogin", method = RequestMethod.GET)
    public ModelAndView SWYTlogin(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWYTlogin");
        return view;
    }

    @RequestMapping(value = "SWYTregistered", method = RequestMethod.GET)
    public ModelAndView SWYTregistered(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWYTregistered");
        return view;
    }

    @RequestMapping(value = "SWYTpassword", method = RequestMethod.GET)
    public ModelAndView SWYTpassword(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWYTpassword");
        return view;
    }
}
