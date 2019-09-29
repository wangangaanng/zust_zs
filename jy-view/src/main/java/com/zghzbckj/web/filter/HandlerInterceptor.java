package com.zghzbckj.web.filter;

import com.zghzbckj.web.controll.ApiConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HandlerInterceptor extends HandlerInterceptorAdapter {



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        final Map<String, Object> model = modelAndView.getModel();

        if (!(modelAndView.getView() instanceof RedirectView)) {
            model.put("imagePath", ApiConstants.imagePath);
            model.put("localUrl", ApiConstants.localUrl);
            model.put("uploadUrl", ApiConstants.uploadUrl);
            model.put("webname", ApiConstants.webname);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
