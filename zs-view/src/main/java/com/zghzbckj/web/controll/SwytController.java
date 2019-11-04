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
        model.addAttribute("uploadUrl", ApiConstants.uploadUrl);
    }
   // 三位一体框首页容器
   @RequestMapping(value = "/trinityEnrollment/{pageType}", method = RequestMethod.GET)
   public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String pageType) throws UnsupportedEncodingException {
       view.addObject("page",pageType);
       view.setViewName("trinityEnrollment");
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
