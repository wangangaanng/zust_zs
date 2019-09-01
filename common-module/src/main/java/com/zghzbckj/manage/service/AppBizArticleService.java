/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.dao.AppBizArticleDao;
import com.zghzbckj.manage.dao.AppBizAttDao;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.AppBizArticle;
import com.zghzbckj.manage.entity.AppBizAtt;
import com.zghzbckj.manage.utils.FtlFileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-04-13
 */
@Service
@Transactional(readOnly = true)
public class AppBizArticleService extends CrudService<AppBizArticleDao, AppBizArticle> {

    private static final Logger log = Logger.getLogger(AppBizArticleService.class);

    @Autowired
    AppBizAttService appBizAttService;
    @Autowired
    AppBizAttDao appBizAttDao;

    @Autowired
    CommonDao commonDao;
    @Override
    public AppBizArticle get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<AppBizArticle> findList(AppBizArticle appBizArticle) {
        return super.findList(appBizArticle);
    }

    @Override
    public PageInfo<AppBizArticle> findPage(Page<AppBizArticle> page, AppBizArticle appBizArticle) {
        return super.findPage(page, appBizArticle);
    }

    @Transactional(readOnly = false)
    public void save(AppBizArticle appBizArticle) {
        super.saveOrUpdate(appBizArticle);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(AppBizArticle appBizArticle) {
        super.delete(appBizArticle);
    }


    /**
     * <p>方法:findPageappBizArticle TODO后台AppBizArticle分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageAppBizArticle(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<AppBizArticle> page = findPage(dataMap, pageNo, pageSize, " a.createtime DESC ");
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:saveappBizArticle TODO保存AppBizArticle信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveAppBizArticle(Map<String, Object> mapData) {
        AppBizArticle appBizArticle = JsonUtil.map2Bean(mapData, AppBizArticle.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            AppBizArticle appBizArticleIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(appBizArticle, appBizArticleIndata);
            appBizArticle = appBizArticleIndata;
        }
        saveOrUpdate(appBizArticle);
        return ResponseMessage.sendOK(appBizArticle);
    }

    /**
     * <p>方法:removeOrder TODO多条删除AppBizArticle </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            AppBizArticle appBizArticle = new AppBizArticle();
            appBizArticle.setOwid(owid);
            this.dao.delete(appBizArticle);
            params.put("wzRefOwid", owid);
            appBizAttDao.deleteByMap(params);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public ResponseMessage getDetail(Map<String, Object> mapData) {
        String owid=mapData.get("owid").toString();
        AppBizArticle article=get(owid);
        AppBizAtt param=new AppBizAtt();
        if(article.getYdcs()==null){
            article.setYdcs(0);
        }
        article.setYdcs(article.getYdcs()+1);
        saveOrUpdate(article);
        param.setWzRefOwid(owid);
        List<AppBizAtt> appBizAtts=appBizAttService.findList(param);
        article.setFileList(appBizAtts);
        return ResponseMessage.sendOK(article);
    }
    @Transactional(readOnly = false)
    public ResponseMessage getBeforeList(Map<String, Object> mapData) {
        String isDetail=mapData.get("isDetail").toString();
        mapData.put("wzzt",1);
        if(Integer.valueOf(isDetail)==0){
            return getListArticle(mapData);
        }else{
            return getDetailArticle(mapData);
        }
    }

    private ResponseMessage getListArticle(Map<String, Object> mapData) {
        String pageNo=mapData.get("pageNo").toString();
        String pageSize=mapData.get("pageSize").toString();
        Page<AppBizArticle> page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        mapData.put("page", page);
        mapData.put("orderBy", " a.istop DESC,a.fbsj DESC");
        page.setList(this.dao.findMapByShort(mapData));
        PageInfo<AppBizArticle> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long)page.getTotalPage());
        pageInfo.setCurrentIndex((long)page.getPageNo());
        pageInfo.setPageSize((long)page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long)page.getPageNo());
        return ResponseMessage.sendOK(pageInfo);
    }


    private ResponseMessage getDetailArticle(Map<String, Object> mapData) {
        List<AppBizArticle> articleList=this.dao.getOneByEjLmbh(mapData);
        if(articleList==null||articleList.size()<1){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"暂无数据");
        }
        AppBizArticle  article=articleList.get(0);
        if(article.getYdcs()==null){
            article.setYdcs(0);
        }
        article.setYdcs(article.getYdcs()+1);
        saveOrUpdate(article);
        AppBizAtt param=new AppBizAtt();
        param.setWzRefOwid(article.getOwid());
        List<AppBizAtt> appBizAtts=appBizAttService.findList(param);
        article.setFileList(appBizAtts);
        return ResponseMessage.sendOK(article);
    }

    @Transactional(readOnly = false)
    public ResponseMessage addOwid(Map<String, Object> mapData) {
        AppBizArticle article=new AppBizArticle();
        article.setWzbt("new article");
        saveOrUpdate(article);
        return ResponseMessage.sendOK(article.getOwid());
    }

    @Transactional(readOnly = false)
    public ResponseMessage dealNrAndAtt(Map<String, Object> mapData) {
        AppBizArticle article=JsonUtil.map2Bean(mapData,AppBizArticle.class);
        if(article.getLmbh2().equals("A01")&&TextUtils.isEmpty(article.getTpjj())){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"最新动态的图片简介内容不能为空");
        }
        if(null!=article.getWzbt()&&article.getWzbt().length()>200){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"文章标题过长，不得超过200个字符");
        }
        if(null!=article.getWzly()&&article.getWzly().length()>200){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"文章来源过长，不得超过200个字符");
        }
        if(null!=article.getFbr()&&article.getFbr().length()>30){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"发布人过长,不得超过30个字符");
        }
        if(null!=article.getJjnr()&&article.getJjnr().length()>1000){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"简介过长,不得超过1000个字符");
        }
        if(null!=article.getMemo()&&article.getMemo().length()>240){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"备注过长,不得超过240个字符");
        }
//        if(Integer.valueOf(mapData.get("isSingle").toString())==1){
//            Map parm= Maps.newHashMap();
//            parm.put("lmbh2",article.getLmbh2());
//            List<AppBizArticle> indataList=this.dao.getOneByEjLmbh(parm);
//            if(null!=indataList&&indataList.size()>0) {
//                indata
//                if (null != indata && !(indata.getOwid().equals(article.getOwid()))) {
//                    return ResponseMessage.sendError(ResponseMessage.FAIL, "此栏目为详情，只能包含一篇文章");
//                }
//            }
//        }
        article.setSxsj(DateUtil.getDate(mapData.get("sxsj").toString(), "yyyy-MM-dd"));
        article.setFbsj(DateUtil.getDate(mapData.get("fbsj").toString(), "yyyy-MM-dd"));
        saveOrUpdate(article);
        List<Map> mapList= (List<Map>) mapData.get("attList");
        Map parm= Maps.newHashMap();
        parm.put("wzRefOwid",article.getOwid());
        appBizAttService.deleteByMap(parm);
        for(Map oneAtt:mapList){
            AppBizAtt appBizAtt=JsonUtil.map2Bean(oneAtt,AppBizAtt.class);
            appBizAttDao.insert(appBizAtt);
        }
        genrateHtmlFile(article,mapList);
        return ResponseMessage.sendOK(Boolean.TRUE);
    }

    private void genrateHtmlFile(AppBizArticle article, List<Map> mapList) {
        Map rootDate=Maps.newHashMap();
        rootDate.put("WZBT",article.getWzbt());
        rootDate.put("FBRQ", DateUtil.getDateString(article.getFbsj(),"yyyy-MM-dd"));
        rootDate.put("WZLY",article.getWzly());
        rootDate.put("FBR",article.getFbr());
        rootDate.put("FJLB",mapList);
        rootDate.put("WZNR",article.getWznr());
        rootDate.put("YJLM",article.getLmbt());
        rootDate.put("EJLM",article.getLmbt2());
        String path= Global.getConfig("htmlfile.path");
        FtlFileUtil.freeMarkerContent(rootDate,article.getOwid(),path);
    }

    public ResponseMessage getByEjLmbh(Map<String, Object> mapData) {
        List<AppBizArticle> indataList=this.dao.getOneByEjLmbh(mapData);
        if(null!=indataList&&indataList.size()>0){
            return ResponseMessage.sendOK(indataList.get(0));
        }
        return ResponseMessage.sendOK(null);
    }

    public ResponseMessage genralAll(Map<String, Object> mapData) {
        AppBizArticle param=new AppBizArticle();
        List<AppBizArticle> list=this.findList(param);
        AppBizAtt attParam=new AppBizAtt();
        for(AppBizArticle article:list){
            attParam.setWzRefOwid(article.getOwid());
            List<AppBizAtt> attList=appBizAttService.findList(attParam);
            article.setFileList(attList);
            genrateHtmlFileSec(article);
        }
        return ResponseMessage.sendOK(list.size());
    }

    private void genrateHtmlFileSec(AppBizArticle article) {
        Map rootDate=Maps.newHashMap();
        rootDate.put("WZBT",article.getWzbt());
        rootDate.put("FBRQ", DateUtil.getDateString(article.getFbsj(),"yyyy-MM-dd"));
        rootDate.put("WZLY",article.getWzly());
        rootDate.put("FBR",article.getFbr());
        rootDate.put("FJLB",article.getFileList());
        rootDate.put("WZNR",article.getWznr());
        rootDate.put("YJLM",article.getLmbt());
        rootDate.put("EJLM",article.getLmbt2());
        String path= Global.getConfig("htmlfile.path");
        FtlFileUtil.freeMarkerContent(rootDate,article.getOwid(),path);
    }

    @Transactional( readOnly = false)
    public ResponseMessage saveArticle(Map<String, Object> mapData) throws Exception{
        AppBizArticle article = JsonUtil.map2Bean(mapData, AppBizArticle.class);
        if(TextUtils.isEmpty(article.getOwid())){
            article.setLmbt(CacheUtil.getVal(CommonModuleContant.LMBH+article.getLmbh()));
            article.setLmbt2(CacheUtil.getVal(CommonModuleContant.LMBH2+article.getLmbh2()));
        }
        if (TextUtils.isEmpty(mapData.get("fbsj"))) {
            article.setFbsj(new Date());
        }else{
            article.setFbsj(DateUtil.getDate(mapData.get("fbsj").toString(), CommonConstant.DATE_FROMART));
        }
        if (!TextUtils.isEmpty(mapData.get("sxsj"))) {
            article.setSxsj(DateUtil.getDate(mapData.get("sxsj").toString(), CommonConstant.DATE_FROMART));
        }
        saveOrUpdate(article);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))){
            mapData.put("articleOwid",article.getOwid());
            commonDao.updateFile(mapData);
        }
        Map param=Maps.newHashMap();
        param.put("wzRefOwid",article.getOwid());
        appBizAttService.deleteByMap(param);
        List<Map> files=commonDao.getSysFiles(param);
        for(Map mapFile:files){
            AppBizAtt appBizAtt=new AppBizAtt();
            appBizAtt.setWzRefOwid(article.getOwid());
            appBizAtt.setFjmc(mapFile.get("FILE_LABEL").toString());
            appBizAtt.setFjlj(mapFile.get("FILE_PATH").toString());
            appBizAttService.saveOrUpdate(appBizAtt);
        }
        return ResponseMessage.sendOK(article);
    }

}