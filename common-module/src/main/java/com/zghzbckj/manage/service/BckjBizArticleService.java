/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizArticleDao;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizArticle;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizArticleService extends CrudService<BckjBizArticleDao, BckjBizArticle> {

    private static final Logger log = Logger.getLogger(BckjBizArticleService.class);

    @Autowired
    BckjDicKeysService bckjDicKeysService;
    @Autowired
    CommonDao commonDao;
    @Override
    @Transactional(readOnly = false)
    public BckjBizArticle get(String owid) {
        BckjBizArticle artcle=super.get(owid);
        artcle.setYdcs(artcle.getYdcs()+1);
        saveOrUpdate(artcle);
        Map mapParam= Maps.newHashMap();
        mapParam.put("wzRefOwid",owid);
        List<Map> files=commonDao.getSysFiles(mapParam);
        artcle.setFileList(files);
        return artcle;
    }



    @Override
    public List<BckjBizArticle> findList(BckjBizArticle bckjBizArticle) {
        return super.findList(bckjBizArticle);
    }

    @Override
    public PageInfo<BckjBizArticle> findPage(Page<BckjBizArticle> page, BckjBizArticle bckjBizArticle) {
        return super.findPage(page, bckjBizArticle);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizArticle bckjBizArticle) {
        super.saveOrUpdate(bckjBizArticle);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizArticle bckjBizArticle) {
        super.delete(bckjBizArticle);
    }


    /**
     * <p>方法:findPagebckjBizArticle TODO后台BckjBizArticle分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizArticle(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizArticle> page = findPage(dataMap, pageNo, pageSize, " a.fbsj DESC");
        List<BckjBizArticle> records = page.getRecords();
        BckjBizArticle articleCount = new BckjBizArticle();
        articleCount.setWzbt("共有：" + page.getTotalCount() + "篇文章");
        articleCount.setReadOnly(true);
        records.add(0, articleCount);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizArticle TODO保存BckjBizArticle信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizArticle(Map<String, Object> mapData) {
        BckjBizArticle bckjBizArticle = JsonUtil.map2Bean(mapData, BckjBizArticle.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizArticle bckjBizArticleIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizArticle, bckjBizArticleIndata);
            bckjBizArticle = bckjBizArticleIndata;
        }
        saveOrUpdate(bckjBizArticle);
        return ResponseMessage.sendOK(bckjBizArticle);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizArticle </p>
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
            BckjBizArticle bckjBizArticle = new BckjBizArticle();
            bckjBizArticle.setOwid(owid);
            this.dao.delete(bckjBizArticle);
            params.put("owid", owid);
            commonDao.deleteFiles(params);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
    *<p>方法:getWzList TODO获取文章列表 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.entity.PageInfo  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 17:14  </li>
    *</ul>
    */
    public PageInfo getWzList(Map<String, Object> mapData) {
        String pageNo=MapUtils.getString(mapData,"pageNo");
        String pageSize=MapUtils.getString(mapData,"pageSize");
        Page<BckjBizArticle> page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        mapData.put("page", page);
        mapData.put("orderBy", " a.istop DESC,a.sxh DESC,fbsj DESC");
        page.setList(this.dao.findMapByShort(mapData));
        PageInfo<BckjBizArticle> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long)page.getTotalPage());
        pageInfo.setCurrentIndex((long)page.getPageNo());
        pageInfo.setPageSize((long)page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long)page.getPageNo());
        return pageInfo;
    }

    /**
    *<p>方法:getWzDetail TODO获取二级文章详情 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.manage.entity.BckjBizArticle  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 17:14  </li>
    *</ul>
    */
    @Transactional( readOnly = false)
    public BckjBizArticle getWzDetail(Map<String, Object> mapData) {
        List<BckjBizArticle> articleList=this.dao.findListByMap(mapData);
        if(null!=articleList&&articleList.size()>0){
            BckjBizArticle oneArt=articleList.get(0);
            oneArt.setYdcs(oneArt.getYdcs()+1);
            saveOrUpdate(oneArt);
            Map mapParam= Maps.newHashMap();
            mapParam.put("wzRefOwid",oneArt.getOwid());
            List<Map> files=commonDao.getSysFiles(mapParam);
            oneArt.setFileList(files);
            return oneArt;
        }else{
            return null;
        }
    }

    /**
    *<p>方法:saveArticle TODO保存文章 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 17:13  </li>
    *</ul>
    */
    @Transactional( readOnly = false)
    public ResponseMessage saveArticle(Map<String, Object> mapData) throws Exception{
        BckjBizArticle article = JsonUtil.map2Bean(mapData, BckjBizArticle.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizArticle bckjBizArticleIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(article, bckjBizArticleIndata);
            article = bckjBizArticleIndata;
        }else{
            article.setYdcs(0);
        }
        if (TextUtils.isEmpty(mapData.get("fbsj"))) {
            article.setFbsj(new Date());
        }else{
            article.setFbsj(DateUtil.getDate(mapData.get("fbsj").toString(), CommonConstant.DATE_FROMART));
        }
        if (!TextUtils.isEmpty(mapData.get("sxsj"))) {
            article.setSxsj(DateUtil.getDate(mapData.get("sxsj").toString(), CommonConstant.DATE_FROMART));
        }
        Map mapFilter=Maps.newHashMap();
        mapFilter.put("content",article.getWzbt()+article.getJjnr());
        String filterResult=bckjDicKeysService.filterContent(mapFilter);
        if(!TextUtils.isEmpty(filterResult)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"标题或简介中请去除如下字词"+filterResult);
        }
        mapFilter.put("content",article.getWznr());
         filterResult=bckjDicKeysService.filterContent(mapFilter);
        if(!TextUtils.isEmpty(filterResult)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"文章内容中请去除如下字词"+filterResult);
        }
        saveOrUpdate(article);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))){
            mapData.put("articleOwid",article.getOwid());
            commonDao.updateFile(mapData);
        }
        return ResponseMessage.sendOK(article);
    }



    /**
    *<p>方法:getByEjLmbh TODO获取二级栏目文章，后台 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 17:15  </li>
    *</ul>
    */
    public ResponseMessage getByEjLmbh(Map<String, Object> mapData) {
        List<BckjBizArticle> indataList=this.dao.findListByMap(mapData);
        if(null!=indataList&&indataList.size()>0){
            return ResponseMessage.sendOK(indataList.get(0));
        }
        return ResponseMessage.sendOK(null);
    }

    /**
    *<p>方法:getArticlDeatil TODO带上一条下一条的文章详情 </p>
    *<ul>
     *<li> @param owid TODO</li>
    *<li>@return java.util.Map  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 17:16  </li>
    *</ul>
    */
    @Transactional(readOnly = false)
    public Map getArticlDeatil(String owid) {
        BckjBizArticle article= get(owid);
        article.setYdcs(article.getYdcs()+1);
        saveOrUpdate(article);
        Map mapArticle=Maps.newHashMap();
        BeanUtil.copy2Map(mapArticle,article,"fbr","wzbt","wzly","wznr");
        mapArticle.put("fbsj",DateUtil.getDateString(article.getFbsj(),CommonConstant.DATE_FROMART));
        Map param=Maps.newHashMap();
        param.put("lmbh",article.getLmbh());
        param.put("orderBy"," a.istop DESC,a.sxh desc");
        param.put("sxh"," AND a.sxh < "+ article.getSxh());
        List<BckjBizArticle> mapList=this.dao.findMapByShort(param);
        mapArticle.put("upArticle",0);
        mapArticle.put("downArticle",0);
        if(null!=mapList&&mapList.size()>0){
            mapArticle.put("upArticle",mapList.get(mapList.size()-1));
        }
        param.put("sxh"," AND a.sxh > "+ article.getSxh());
        List<BckjBizArticle> mapList2=this.dao.findMapByShort(param);
        if(null!=mapList2&&mapList2.size()>0){
            mapArticle.put("downArticle",mapList2.get(0));
        }
        return mapArticle;
    }

    /**
    *<p>方法:searchAll TODO查询站点内所有文章数据 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.entity.PageInfo  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/9 16:43  </li>
    *</ul>
    */
    public PageInfo searchAll(Map<String, Object> mapData) {
            String pageNo=MapUtils.getString(mapData,"pageNo");
            String pageSize=MapUtils.getString(mapData,"pageSize");
            Page<BckjBizArticle> page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
            mapData.put("page", page);
            mapData.put("orderBy", " a.istop DESC,a.sxh DESC,fbsj DESC");
            page.setList(this.dao.findMapByKey(mapData));
            PageInfo<BckjBizArticle> pageInfo = new PageInfo();
            pageInfo.setRecords(page.getList());
            pageInfo.setTotalPage((long)page.getTotalPage());
            pageInfo.setCurrentIndex((long)page.getPageNo());
            pageInfo.setPageSize((long)page.getPageSize());
            pageInfo.setTotalCount(page.getCount());
            pageInfo.setCurrentPage((long)page.getPageNo());
            return pageInfo;
    }



    /**
     *<p>方法:searchAll TODO查询一级栏目下所有文章 </p>
     *<ul>
     *<li> @param mapData TODO</li>
     *<li>@return com.zghzbckj.base.entity.PageInfo  </li>
     *<li>@author D.chen.g </li>
     *<li>@date 2019/10/9 16:43  </li>
     *</ul>
     */
    public PageInfo searchByYjlm(Map<String, Object> mapData) {
        String pageNo=MapUtils.getString(mapData,"pageNo");
        String pageSize=MapUtils.getString(mapData,"pageSize");
        Page<BckjBizArticle> page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        mapData.put("page", page);
        mapData.put("orderBy", " a.istop DESC,a.sxh DESC,fbsj DESC");
        page.setList(this.dao.findYjBylmbh(mapData));
        PageInfo<BckjBizArticle> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long)page.getTotalPage());
        pageInfo.setCurrentIndex((long)page.getPageNo());
        pageInfo.setPageSize((long)page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long)page.getPageNo());
        return pageInfo;
    }
}