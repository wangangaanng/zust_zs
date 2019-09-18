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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.dao.BckjBizArticleDao;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizArticle;
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
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizArticleService extends CrudService<BckjBizArticleDao, BckjBizArticle> {

    private static final Logger log = Logger.getLogger(BckjBizArticleService.class);

    @Autowired
    CommonDao commonDao;
    @Override
    @Transactional(readOnly = false)
    public BckjBizArticle get(String owid) {
         BckjBizArticle artcle=super.get(owid);
        artcle.setYdcs(artcle.getYdcs()+1);
        saveOrUpdate(artcle);
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
        PageInfo<BckjBizArticle> page = findPage(dataMap, pageNo, pageSize, null);
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

    public PageInfo getWzList(Map<String, Object> mapData) {
        String pageNo=mapData.get("pageNo").toString();
        String pageSize=mapData.get("pageSize").toString();
        Page<BckjBizArticle> page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        mapData.put("page", page);
        mapData.put("orderBy", " a.istop DESC,a.sxh ");
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

    public BckjBizArticle getWzDetail(Map<String, Object> mapData) {
        List<BckjBizArticle> articleList=this.dao.findListByMap(mapData);
        if(null!=articleList&&articleList.size()>0){
            BckjBizArticle oneArt=articleList.get(0);
            Map mapParam= Maps.newHashMap();
            mapParam.put("wzRefOwid",oneArt.getOwid());
            List<Map> files=commonDao.getSysFiles(mapParam);
            oneArt.setFileList(files);
            return oneArt;
        }else{
            return null;
        }
    }

    @Transactional( readOnly = false)
    public ResponseMessage saveArticle(Map<String, Object> mapData) throws Exception{
        BckjBizArticle article = JsonUtil.map2Bean(mapData, BckjBizArticle.class);
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
        return ResponseMessage.sendOK(article);
    }

    //生成所有文章代码
    public ResponseMessage genralAll(Map<String, Object> mapData) {
        BckjBizArticle param=new BckjBizArticle();
        List<BckjBizArticle> list=this.findList(param);
        Map attParam=Maps.newHashMap();
        for(BckjBizArticle article:list){
            attParam.put("wzRefOwid",article.getOwid());
            List<Map> attList=commonDao.getSysFiles(attParam);
            article.setFileList(attList);
            genrateHtmlFile(article);
        }
        return ResponseMessage.sendOK(list.size());
    }
    private void genrateHtmlFile(BckjBizArticle article) {
        Map rootDate=Maps.newHashMap();
        rootDate.put("WZBT",article.getWzbt());
        rootDate.put("FBRQ", DateUtil.getDateString(article.getFbsj(),CommonConstant.DATE_FROMART));
        rootDate.put("WZLY",article.getWzly());
        rootDate.put("FBR",article.getFbr());
        rootDate.put("FJLB",article.getFileList());
        rootDate.put("WZNR",article.getWznr());
        rootDate.put("EJLM",article.getLmbh());
        String path= Global.getConfig(CommonModuleContant.HPATH);
        FtlFileUtil.freeMarkerContent(rootDate,article.getOwid(),path);
    }

    public ResponseMessage getByEjLmbh(Map<String, Object> mapData) {
        List<BckjBizArticle> indataList=this.dao.findListByMap(mapData);
        if(null!=indataList&&indataList.size()>0){
            return ResponseMessage.sendOK(indataList.get(0));
        }
        return ResponseMessage.sendOK(null);
    }
}