/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.dao.BckjBizCjxxDao;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizCjxx;
import com.zghzbckj.manage.entity.BckjBizJbxx;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class BckjBizCjxxService extends CrudService<BckjBizCjxxDao, BckjBizCjxx> {

    private static final Logger log = Logger.getLogger(BckjBizCjxxService.class);

    @Autowired
    BckjBizJbxxService bckjBizJbxxService;
    @Autowired
    CommonDao commonDao;
    @Override
    public BckjBizCjxx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizCjxx> findList(BckjBizCjxx bckjBizCjxx) {
        return super.findList(bckjBizCjxx);
    }

    @Override
    public PageInfo<BckjBizCjxx> findPage(Page<BckjBizCjxx> page, BckjBizCjxx bckjBizCjxx) {
        return super.findPage(page, bckjBizCjxx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizCjxx bckjBizCjxx) {
        super.saveOrUpdate(bckjBizCjxx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizCjxx bckjBizCjxx) {
        super.delete(bckjBizCjxx);
    }


    /**
     * <p>方法:findPagebckjBizCjxx TODO后台BckjBizCjxx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.PageInfo  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public PageInfo<BckjBizCjxx> findPageBckjBizCjxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizCjxx> page = findPage(dataMap, pageNo, pageSize, null);
        return page;
    }

    /**
     * <p>方法:savebckjBizCjxx TODO保存BckjBizCjxx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizCjxx  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BckjBizCjxx saveBckjBizCjxx(Map<String, Object> mapData) {
        BckjBizCjxx bckjBizCjxx = JsonUtil.map2Bean(mapData, BckjBizCjxx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizCjxx bckjBizCjxxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizCjxx, bckjBizCjxxIndata);
            bckjBizCjxx = bckjBizCjxxIndata;
        }
        saveOrUpdate(bckjBizCjxx);
        return bckjBizCjxx;
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizCjxx </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return java.util.List  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = Lists.newArrayList();
        for (String owid : codes) {
            Map<String, Object> params = Maps.newHashMap();
            BckjBizCjxx bckjBizCjxx = new BckjBizCjxx();
            bckjBizCjxx.setOwid(owid);
            this.dao.delete(bckjBizCjxx);
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }

    /**
    *<p>方法:finishHk TODO完善会考成绩 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/24 13:52  </li>
    *</ul>
    */
    @Transactional(readOnly = false)
    public boolean finishHk(Map<String, Object> mapData) throws CustomerException {
        BckjBizJbxx jbxx=bckjBizJbxxService.getInfo(mapData);
        if(null==jbxx){
            throw CustomerException.newInstances("用户基本信息不存在");
        }
        if(jbxx.getJtcyState()!=1) {
            jbxx.setHkState(1);
            bckjBizJbxxService.saveOrUpdate(jbxx);
        }
        mapData.put("lx","0");
        mapData.put("lx2",2);
        this.dao.deleteByHkZh(mapData);
        saveList(mapData,"zhList",0);
        saveList(mapData,"hkList",2);
        return Boolean.TRUE;
    }

    /**
     *
     * @param dataMap
     * @param listName  列表参数名
     * @param lx 类型
     */
    private void saveList(Map<String, Object> dataMap, String listName, int lx) {
        List<Map<String,Object> >dataList= (List<Map<String, Object>>) dataMap.get(listName);
        for(Map<String,Object> mapOne:dataList){
            BckjBizCjxx jtcyxx=JsonUtil.map2Bean(mapOne,BckjBizCjxx.class);
            jtcyxx.setYhRefOwid(MapUtils.getString(dataMap,"yhRefOwid"));
            jtcyxx.setLx(lx);
            saveOrUpdate(jtcyxx);
        }
    }


    /**
    *<p>方法:finishXk TODO完善选考成绩 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/24 14:52  </li>
    *</ul>
    */
    public boolean finishXk(Map<String, Object> mapData) throws CustomerException{
        BckjBizJbxx jbxx=bckjBizJbxxService.getInfo(mapData);
        if(null==jbxx){
            throw CustomerException.newInstances("用户基本信息不存在");
        }
        if(jbxx.getJtcyState()!=1) {
            jbxx.setXkState(1);
        }
        BckjBizJbxx param=JsonUtil.map2Bean(mapData,BckjBizJbxx.class);
        BeanUtil.copyPropertiesIgnoreNull(param,jbxx);
        bckjBizJbxxService.saveOrUpdate(jbxx);
        saveList(mapData,"xkList",1);
        mapData.put("jbxxOwid",jbxx.getOwid());
        this.commonDao.deleteFilesByjbxx(mapData);
        this.commonDao.updateFileByjbxx(mapData);
        return Boolean.TRUE;
    }

    /**
    *<p>方法:getXkcj TODO获取选考成绩 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/25 14:27  </li>
    *</ul>
    */
    public Map<String,Object> getXkcj(Map<String, Object> mapData) throws CustomerException{
        BckjBizJbxx jbxx=bckjBizJbxxService.getInfo(mapData);
        if(null==jbxx){
            throw CustomerException.newInstances("用户基本信息不存在");
        }
        Map<String,Object> result= JackSonJsonUtils.objectToMap(jbxx);
        Map<String,Object>  paramsCjxx=Maps.newHashMap();
        paramsCjxx.put("yhRefOwid",jbxx.getOwid());
        paramsCjxx.put("lx",1);
        paramsCjxx.put("orderBy","a.xssx");
        result.put("xkList",this.dao.findListByMap(paramsCjxx));
        List<Map> fileList=commonDao.findFileByJbxx(jbxx.getOwid());
        result.put("jsfj",fileList);
        return result;
    }
}