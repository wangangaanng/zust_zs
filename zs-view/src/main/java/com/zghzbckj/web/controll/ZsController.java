package com.zghzbckj.web.controll;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.PropertiesUtil;
import com.zghzbckj.web.utils.UnionHttpUtils;
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
import java.util.HashMap;
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
    @RequestMapping(value = {"/","index","IndexPage!shouYe.htm"}, method = RequestMethod.GET)
    public ModelAndView ZSindex(HttpServletRequest request,ModelAndView view) {
        view.setViewName("ZSindex");
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        //顶部广告
        Map paramgg=Maps.newHashMap();
        paramgg.put("dicType","10028");
        PublicData publicDatagg= UnionHttpUtils.manageParam(paramgg,"zustcommon/common/getByType");
        ResponseMessage gg  = UnionHttpUtils.doPosts(publicDatagg);
        view.addObject("gg",gg.getBean());

        Map param=Maps.newHashMap();
        //轮播图
        param.put("lmbh","127");
        param.put("lx","0");
        param.put("zszd","0");
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjBizPicvid/getPicList");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        view.addObject("sliderList",result.getBean());
        //通知公告-取招生动态前几条
        Map param2=Maps.newHashMap();
        param2.put("lmbh","119");
//        param2.put("wzzt","1");
//        param2.put("isDetail","1");
        param2.put("gjz","");
        param2.put("pageNo", '1');
        param2.put("pageSize", "7");
        PublicData publicData2= UnionHttpUtils.manageParam(param2,"zustcommon/bckjBizArticle/searchByYjlm");
        ResponseMessage result2  = UnionHttpUtils.doPosts(publicData2);
        view.addObject("tzggList",result2.getBean());
        //招生专业关键字
        Map param3=Maps.newHashMap();
        param3.put("dicType","10026");
        PublicData publicData3= UnionHttpUtils.manageParam(param3,"zustcommon/common/getByType");
        ResponseMessage result3  = UnionHttpUtils.doPosts(publicData3);
        view.addObject("zszyList",result3.getBean());

        //计划查询条件
        Map param4=Maps.newHashMap();
        param4.put("nf", "");
        param4.put("sf", "");
        param4.put("kl", "");
        param4.put("pc", "");
        param4.put("zy", "");
        PublicData publicData4 = UnionHttpUtils.manageParam(param4, "zustzs/bckjBizZsjh/getChanges");
        ResponseMessage result4 = UnionHttpUtils.doPosts(publicData4);
        view.addObject("conditionJh", result4.getBean());

        //历年查询条件
        Map param5=Maps.newHashMap();
        param5.put("nf", "");
        param5.put("sf", "");
        param5.put("kl", "");
        param5.put("pc", "");
        param5.put("zy", "");
        PublicData publicData5 = UnionHttpUtils.manageParam(param5, "zustzs/bckjBizLntj/getChanges");
        ResponseMessage result5 = UnionHttpUtils.doPosts(publicData5);
        view.addObject("conditionLn", result5.getBean());

        //获取栏目名称-招生专业
        Map param6=Maps.newHashMap();
        param6.put("wzbh","0");
        param6.put("lmbh","129");
        PublicData publicData6= UnionHttpUtils.manageParam(param6,"zustcommon/bckjDicMenu/getLmmc");
        ResponseMessage result6  = UnionHttpUtils.doPosts(publicData6);
        view.addObject("zszyLmmc",result6.getBean());
        return view;
    }
    @RequestMapping(value = "zszy", method = RequestMethod.GET)
    public ModelAndView ZSzszy(HttpServletRequest request,ModelAndView view) {
        view.setViewName("ZSzszy");
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());

        Map param2=Maps.newHashMap();
        param2.put("parentId","-1");
        PublicData publicData1= UnionHttpUtils.manageParam(param2,"zustcommon/bckjBizXyzy/getZyList");
        ResponseMessage result1  = UnionHttpUtils.doPosts(publicData1);
        List<Map> beanList = (List<Map>) result1.getBean();
        ResponseMessage resultMess  = new ResponseMessage();
        if(beanList!=null&&beanList.size()>0){
            view.addObject("xyList",beanList);
            List zyList= Lists.newArrayList();
            for(Map map:beanList){
                String owid = map.get("owid").toString();
                Map paramn=Maps.newHashMap();
                paramn.put("parentId",owid);
                PublicData _data= UnionHttpUtils.manageParam(paramn,"zustcommon/bckjBizXyzy/getZyList");
                resultMess = UnionHttpUtils.doPosts(_data);

                zyList.add(resultMess.getBean());

            }
            view.addObject("zyList",zyList);
        }

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
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        String bxlx=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("BXLX").toString();
        String lmbh=((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("CODE").toString();
        if(bxlx.equals("1")){//列表
            view.setViewName("ZSnewsList");
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
        }else{
            view.setViewName("ZSarticleTpl");
            Map param=Maps.newHashMap();
            param.put("lmbh",lmbh);
            param.put("wzzt","1");
            param.put("isDetail",bxlx);
            PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjBizArticle/getMuArticle");
            ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
            view.addObject("bxlx",bxlx);
            view.addObject("result",result.getBean());
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
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
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

    @RequestMapping(value = "wzOrTpOrSqnd/{lmbh}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView newsyList(HttpServletRequest request,ModelAndView view, @PathVariable String lmbh, @PathVariable String thirdDir) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            key = new String(key.getBytes("ISO-8859-1"),"utf-8");
        }else {
            key="";
        }
        view.setViewName("ZSnewsyList");
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("lmbh",lmbh);
        //获取栏目名称
        Map param6=Maps.newHashMap();
        param6.put("wzbh","0");
        param6.put("lmbh",lmbh);
        PublicData publicData6= UnionHttpUtils.manageParam(param6,"zustcommon/bckjDicMenu/getLmmc");
        ResponseMessage result6  = UnionHttpUtils.doPosts(publicData6);
        if((null!=((Map) result6.getBean()))&&(null!=((Map) result6.getBean()).get("NAME"))) {
            view.addObject("secondDirName",((Map) result6.getBean()).get("NAME"));
        }else{
            view.addObject("secondDirName","");
        }
        view.addObject("thirdDir",thirdDir);

        //招生计划年度
        view.addObject("thirdDirName",  ((List<Map>) getZsYears().getBean()).get(Integer.valueOf(thirdDir)).get("dicVal2").toString());
        view.addObject("menuList",((List<Map>) getZsYears().getBean()));
        String nf=((List<Map>) getZsYears().getBean()).get(Integer.valueOf(thirdDir)).get("dicVal1").toString();
        Map param = Maps.newHashMap();
        param.put("lmbh",lmbh);
        param.put("wzzt","1");
        param.put("isDetail","1");
        param.put("nf",nf);
        param.put("gjz",key);
        param.put("pageNo", '1');
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }
        return view;
    }

    @RequestMapping(value = "wzOrTpOrSqnd/{lmbh}/{thirdDir}/{currentPage}", method = RequestMethod.GET)
    public ModelAndView newsyList(HttpServletRequest request,ModelAndView view, @PathVariable String lmbh, @PathVariable String thirdDir,@PathVariable String currentPage) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            view.addObject("key", key);
        }else {
            view.addObject("key","");
        }
        view.setViewName("ZSnewsyList");
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("lmbh",lmbh);
        //获取栏目名称
        Map param6=Maps.newHashMap();
        param6.put("wzbh","0");
        param6.put("lmbh",lmbh);
        PublicData publicData6= UnionHttpUtils.manageParam(param6,"zustcommon/bckjDicMenu/getLmmc");
        ResponseMessage result6  = UnionHttpUtils.doPosts(publicData6);
        if((null!=((Map) result6.getBean()))&&(null!=((Map) result6.getBean()).get("NAME"))) {
            view.addObject("secondDirName",((Map) result6.getBean()).get("NAME"));
        }else{
            view.addObject("secondDirName","");
        }
        view.addObject("thirdDir",thirdDir);

        //招生计划年度
        view.addObject("thirdDirName",  ((List<Map>) getZsYears().getBean()).get(Integer.valueOf(thirdDir)).get("dicVal2").toString());
        view.addObject("menuList",((List<Map>) getZsYears().getBean()));
        String nf=((List<Map>) getZsYears().getBean()).get(Integer.valueOf(thirdDir)).get("dicVal1").toString();
        Map param = Maps.newHashMap();
        param.put("lmbh",lmbh);
        param.put("wzzt","1");
        param.put("isDetail","1");
        param.put("nf",nf);
        param.put("gjz",key);
        param.put("pageNo", currentPage);
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }
        return view;
    }

    @RequestMapping(value = "wzOrTpOrSqLm/{lmbh}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView newslmList(HttpServletRequest request,ModelAndView view, @PathVariable String lmbh,@PathVariable String thirdDir) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            key = new String(key.getBytes("ISO-8859-1"),"utf-8");
        }else {
            key="";
        }
        view.setViewName("ZSnewslmList");
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());

        view.addObject("lmbh",lmbh);
        //获取栏目名称
        Map param6=Maps.newHashMap();
        param6.put("wzbh","0");
        param6.put("lmbh",lmbh);
        PublicData publicData6= UnionHttpUtils.manageParam(param6,"zustcommon/bckjDicMenu/getLmmc");
        ResponseMessage result6  = UnionHttpUtils.doPosts(publicData6);
        if((null!=((Map) result6.getBean()))&&(null!=((Map) result6.getBean()).get("NAME"))) {
            view.addObject("secondDirName",((Map) result6.getBean()).get("NAME"));
        }else{
            view.addObject("secondDirName","");
        }
        view.addObject("thirdDir",thirdDir);


        //获取子栏目
        Map param5=Maps.newHashMap();
        param5.put("wzbh","0");
        param5.put("fid",lmbh);
        PublicData publicData5= UnionHttpUtils.manageParam(param5,"zustcommon/bckjDicMenu/getLmMenu");
        ResponseMessage result5  = UnionHttpUtils.doPosts(publicData5);
        view.addObject("menuList",(List<Map>) result5.getBean());
        view.addObject("thirdDirName",((List<Map>) result5.getBean()).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        String lmid=((List<Map>) result5.getBean()).get(Integer.valueOf(thirdDir)).get("CODE").toString();
        Map param = Maps.newHashMap();
        param.put("lmbh",lmid);
        param.put("wzzt","1");
        param.put("isDetail","1");
        param.put("gjz",key);
        param.put("pageNo", '1');
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }

        return view;
    }

    @RequestMapping(value = "wzOrTpOrSqLm/{lmbh}/{thirdDir}/{currentPage}", method = RequestMethod.GET)
    public ModelAndView newslmList(HttpServletRequest request,ModelAndView view,@PathVariable String lmbh,@PathVariable String thirdDir ,@PathVariable String currentPage) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            view.addObject("key", key);
        }else {
            view.addObject("key","");
        }
        view.setViewName("ZSnewslmList");
        view.addObject("key",key);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());

        view.addObject("lmbh",lmbh);
        //获取栏目名称
        Map param6=Maps.newHashMap();
        param6.put("wzbh","0");
        param6.put("lmbh",lmbh);
        PublicData publicData6= UnionHttpUtils.manageParam(param6,"zustcommon/bckjDicMenu/getLmmc");
        ResponseMessage result6  = UnionHttpUtils.doPosts(publicData6);
        if((null!=((Map) result6.getBean()))&&(null!=((Map) result6.getBean()).get("NAME"))) {
            view.addObject("secondDirName",((Map) result6.getBean()).get("NAME"));
        }else{
            view.addObject("secondDirName","");
        }
        view.addObject("thirdDir",thirdDir);


        //获取子栏目
        Map param5=Maps.newHashMap();
        param5.put("wzbh","0");
        param5.put("fid",lmbh);
        PublicData publicData5= UnionHttpUtils.manageParam(param5,"zustcommon/bckjDicMenu/getLmMenu");
        ResponseMessage result5  = UnionHttpUtils.doPosts(publicData5);
        view.addObject("menuList",(List<Map>) result5.getBean());
        view.addObject("thirdDirName",((List<Map>) result5.getBean()).get(Integer.valueOf(thirdDir)).get("NAME").toString());

        String lmid=((List<Map>) result5.getBean()).get(Integer.valueOf(thirdDir)).get("CODE").toString();
        Map param = Maps.newHashMap();
        param.put("lmbh",lmid);
        param.put("wzzt","1");
        param.put("isDetail","1");
        param.put("gjz",key);
        param.put("pageNo", currentPage);
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/getMuArticle");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }

        return view;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView newsList(HttpServletRequest request,ModelAndView view) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            view.addObject("key",key);
        }else {
            view.addObject("key","");
        }
        view.setViewName("ZSsearch");

        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        Map param = Maps.newHashMap();
        param.put("wzbh",'0');
        param.put("gjz",key);
        param.put("pageNo", '1');
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/searchAll");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }

        return view;
    }
    @RequestMapping(value = "search/{currentPage}", method = RequestMethod.GET)
    public ModelAndView newsList(HttpServletRequest request,ModelAndView view,@PathVariable String currentPage) throws UnsupportedEncodingException {
        String key = PropertiesUtil.filterChar(request.getParameter("key"));
        if(null!=key){
            view.addObject("key",key);
        }else {
            view.addObject("key","");
        }
        view.setViewName("ZSsearch");

        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());

        view.addObject("header",getHeader().getBean());
        Map param = Maps.newHashMap();
        param.put("wzbh",'0');
        param.put("gjz",key);
        param.put("pageNo", currentPage);
        param.put("pageSize", "20");
        ResponseMessage resultMess  = new ResponseMessage();
        PublicData _data = UnionHttpUtils.manageParam(param, "zustcommon/bckjBizArticle/searchAll");
        resultMess = UnionHttpUtils.doPosts(_data);
        if(null!=resultMess.getBean()) {
            view.addObject("result",(Map) resultMess.getBean());
        }

        return view;
    }

    @RequestMapping(value = "wzxq/{owid}", method = RequestMethod.GET)
        public ModelAndView wzxq(HttpServletRequest request,ModelAndView view, @PathVariable String owid) {
        view.setViewName("ZSnewsDetail");
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        Map param=Maps.newHashMap();
        param.put("owid",owid);
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjBizArticle/getArticlDeatil");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        view.addObject("result",result.getBean());
        return view;
    }


    public ResponseMessage getHeader() {
        Map param= Maps.newHashMap();
        param.put("wzbh","0");
        param.put("fid","-1");
        param.put("bxlx","1");
        PublicData publicData= UnionHttpUtils.manageParam(param,"/zustcommon/bckjDicMenu/getSyMenu");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);

        //招生计划
        Map param2= Maps.newHashMap();
        param2.put("dicType","10027");
        PublicData publicData2= UnionHttpUtils.manageParam(param2,"zustcommon/common/getByType");
        ResponseMessage year  = UnionHttpUtils.doPosts(publicData2);
        return result;
    }
    public ResponseMessage getZsYears() {
        //招生计划
        Map param= Maps.newHashMap();
        param.put("dicType","10027");
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/common/getByType");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        return result;
    }
    public ResponseMessage getFooter(){
        //底部链接友情链接
        Map param=Maps.newHashMap();
        param.put("lmbh","128");
        param.put("lx","2");
        param.put("zszd","0");
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjBizPicvid/getPicList");
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
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
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
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
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
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        Map<String, Object> params = new HashMap<>();
        params.put("nf", "");
        params.put("sf", "");
        params.put("kl", "");
        params.put("pc", "");
        params.put("zy", "");
        PublicData publicData = UnionHttpUtils.manageParam(params, "zustzs/bckjBizLntj/getChanges");
        ResponseMessage responseMessage = UnionHttpUtils.doPosts(publicData);
        view.addObject("result", responseMessage.getBean());
        return view;
    }

    /**
     *<p>功能描述:招生网录取通知书查询 lqtzscx</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/28 11:02</li>
     *</ul>
     */
    @RequestMapping(value = "lqtzscx/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zsLqtzscx(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSlqtzscx");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        return view;
    }

    /**
     *<p>功能描述:招生网招生计划查询 zsjhcx</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/28 15:23</li>
     *</ul>
     */
    @RequestMapping(value = "zsjhcx/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zsjhcx(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSzsjhcx");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        Map<String, Object> params = new HashMap<>();
        params.put("nf", "");
        params.put("sf", "");
        params.put("kl", "");
        params.put("pc", "");
        params.put("zy", "");
        PublicData publicData = UnionHttpUtils.manageParam(params, "zustzs/bckjBizZsjh/getChanges");
        ResponseMessage responseMessage = UnionHttpUtils.doPosts(publicData);
        view.addObject("result", responseMessage.getBean());
        return view;
    }

    /**
     *<p>功能描述:招生网在线提问 zxtw</p >
     *<ul>
     *<li>@param [request, view]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/28 15:26</li>
     *</ul>
     */
    @RequestMapping(value = "zxtw/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView zxtw(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSzxtw");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", "1");
        params.put("pageSize", "10");
        //4 招生在线咨询
        params.put("zxlx", "4");
        PublicData publicData = UnionHttpUtils.manageParam(params, "zustcommon/bckjBizZxzx/historyMessage");
        ResponseMessage result = UnionHttpUtils.doPosts(publicData);
        view.addObject("result", (Map) result.getBean());
        return view;
    }

    @RequestMapping(value = "zxtw/{secondDir}/{thirdDir}/{currentPage}", method = RequestMethod.GET)
    public ModelAndView zxtw(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir, @PathVariable String currentPage) {
        view.setViewName("ZSzxtw");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", currentPage);
        params.put("pageSize", "10");
        //1 招生在线咨询
        params.put("zxlx", "4");
        PublicData publicData = UnionHttpUtils.manageParam(params, "zustcommon/bckjBizZxzx/historyMessage");
        ResponseMessage result = UnionHttpUtils.doPosts(publicData);
        view.addObject("result", (Map) result.getBean());
        return view;
    }

    /**
     * <p>功能描述:招生网问卷调查 wjdc</p >
     * <ul>
     * <li>@param [request, view]</li>
     * <li>@return org.springframework.web.servlet.ModelAndView</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/29 9:09</li>
     * </ul>
     */
    @RequestMapping(value = "wjdc/{secondDir}/{thirdDir}", method = RequestMethod.GET)
    public ModelAndView wjdc(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSwjdc");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        return view;
    }

    /**
     *<p>功能描述:调查问卷详情 inquiryDetail</p >
     *<ul>
     *<li>@param [request, view, owid, stuOwid]</li>
     *<li>@return org.springframework.web.servlet.ModelAndView</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/2 15:51</li>
     *</ul>
     */
    @RequestMapping(value = "inquiryDetail/{owid}", method = RequestMethod.GET)
    public ModelAndView inquiryDetail(HttpServletRequest request,ModelAndView view, @PathVariable String owid) {
        view.setViewName("inquiryDetail");
        view.addObject("owid",owid);
        view.addObject("header",getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        Map param=Maps.newHashMap();
        param.put("dcwjRefOwid",owid);
        //招生网 0
        param.put("wzbh",0);
        PublicData publicData= UnionHttpUtils.manageParam(param,"zustcommon/bckjBizDcwj/dcwjDetail");
        ResponseMessage result  = UnionHttpUtils.doPosts(publicData);
        view.addObject("result",result.getBean());
        return view;
    }

    /**
     * <p>功能描述:科院掠影 kyly</p >
     * <ul>
     * <li>@param [request, view]</li>
     * <li>@return org.springframework.web.servlet.ModelAndView</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/29 9:21</li>
     * </ul>
     */
    @RequestMapping(value = "kyly/{secondDir}/{thirdDir}")
    public ModelAndView kyly(HttpServletRequest request, ModelAndView view, @PathVariable String secondDir, @PathVariable String thirdDir) {
        view.setViewName("ZSkyly");
        view.addObject("header", getHeader().getBean());
        view.addObject("headerY",getZsYears().getBean());
        view.addObject("footer",getFooter().getBean());
        view.addObject("secondDir",secondDir);
        view.addObject("thirdDir",thirdDir);
        view.addObject("secondDirName",((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("NAME").toString());
        view.addObject("thirdDirName",  ((List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu"))).get(Integer.valueOf(thirdDir)).get("NAME").toString());
        view.addObject("menuList",(List<Map>) (((List<Map>) getHeader().getBean()).get(Integer.valueOf(secondDir)).get("chirdMenu")));
        Map<String, Object> params = new HashMap<>();
        //lmbh栏目编号 安吉风光117 小和山风光118
        if ("0".equals(thirdDir)) {
            params.put("lmbh", "117");
        } else {
            params.put("lmbh", "118");
        }
        //lx类型 0图片 1视频 2友情链接
        params.put("lx", "0");
        //zszd展示终端 0PC 1手机
        params.put("zszd", "0");
        PublicData publicData = UnionHttpUtils.manageParam(params, "zustcommon/bckjBizPicvid/getPicList");
        ResponseMessage responseMessage = UnionHttpUtils.doPosts(publicData);
        view.addObject("result", (List)responseMessage.getBean());
        return view;
    }

}

