package com.zghzbckj.web.controll;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    @ModelAttribute
    public void setConfig(Model model) {
        model.addAttribute("imagePath", ApiConstants.imagePath);
        model.addAttribute("localUrl", ApiConstants.localUrl);
        model.addAttribute("uploadUrl", ApiConstants.uploadUrl);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWYTindex");
        return view;
    }
    @RequestMapping(value = "SWTYlogin", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWTYlogin");
        return view;
    }
}
