package com.zghzbckj.web.controll;

import com.google.common.collect.Maps;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.PropertiesUtil;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
    @RequestMapping(value = "wzOrTpOrSq/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            key = new String(key.getBytes("ISO-8859-1"),"utf-8");
        }else {
            key="";
        }
        view.setViewName("ZSnewsList");
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        String bxlx=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("BXLX").toString();
        String lmbh=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("CODE").toString();
        if(bxlx.equals("1")){//列表
            view.addObject("bxlx",bxlx);
            Map param = Maps.newHashMap();
            param.put("lmbh",lmbh);
            param.put("wzzt","1");
            param.put("isDetail",bxlx);
            param.put("gjz",key);
            param.put("pageNo", '1');
            param.put("pageSize", "20");
            ResponseMessage resultMess  = new ResponseMessage();
            PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
            resultMess = UnionHttpUtils.doPosts(_data);
            if(null!=resultMess.getBean()) {
                view.addObject("result",(Map) resultMess.getBean());
            }

        }
        return view;
    }

    @RequestMapping(value = "wzOrTpOrSq/{secondDir}/{thirdDir}/{currentPage}", method = RequestMethod.GET)
    public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir,@PathVariable String currentPage) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            view.addObject("key", key);
        }else {
            view.addObject("key","");
        }
        view.setViewName("ZSnewsList");
        view.addObject("header",getHeader().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        String bxlx=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("BXLX").toString();
        String lmbh=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("CODE").toString();
        if(bxlx.equals("1")){//列表
            view.addObject("bxlx",bxlx);
            Map param = Maps.newHashMap();
            param.put("lmbh",lmbh);
            param.put("wzzt","1");
            param.put("isDetail",bxlx);
            param.put("gjz",key);
            param.put("pageNo", currentPage);
            param.put("pageSize", "20");
            ResponseMessage resultMess  = new ResponseMessage();
            PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
            resultMess = UnionHttpUtils.doPosts(_data);
            if(null!=resultMess.getBean()) {
                view.addObject("result",(Map) resultMess.getBean());
            }

        }
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

    /**
     *<p>功能描述:招生网成绩查询 cjcx</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/25 15:02</li>
     *</ul>
     */
    @RequestMapping(value = "cjcx/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zsCjcx(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZScjcx");
        view.addObject("header", getHeader().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        return view;
    }

    /**
     *<p>功能描述:招生网录取查询 lqcx</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/25 15:04</li>
     *</ul>
     */
    @RequestMapping(value = "lqcx/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zsLqcx(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSlqcx");
        view.addObject("header", getHeader().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        return view;
    }

    /**
     *<p>功能描述:招生网历年分数名次查询 lnfsmc</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/25 15:06</li>
     *</ul>
     */
    @RequestMapping(value = "lnfsmc/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zsLnfsmc(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSlnfsmc");
        view.addObject("header", getHeader().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        return view;
    }
}

