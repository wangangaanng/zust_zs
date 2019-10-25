package com.zghzbckj.web.controll;

import com.google.common.collect.Maps;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>方法 DemoController : <p>
 * <p>说明:Spring Mvc 测试类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 20:54
 * </pre>
 */
@Controller
public class ZsController {
    private static final Logger log = Logger.getLogger(ZsController.class);
    @ModelAttribute
    public void setConfig(Model model) {
        model.addAttribute("imagePath", ApiConstants.imagePath);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView ZSindex(HttpServletRequest request,ModelAndView view) {
        view.setViewName("ZSindex");
        view.addObject("header",getHeader().getBean());
        return view;
    }

    @RequestMapping(value = "/swyt", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWYTindex");
        return view;
    }
    @RequestMapping(value = "SWTYlogin", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWTYlogin");
        return view;
    }

    public ResponseMessage getHeader() {
        Map param= Maps.newHashMap();
        param.put("wzbh","0");
        param.put("fid","-1");
        param.put("bxlx","1");
        PublicData publicData= UnionHttpUtils.manageParam(param,"/zustcommon/bckjDicMenu/getSyMenu");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        return result;
    }
}

