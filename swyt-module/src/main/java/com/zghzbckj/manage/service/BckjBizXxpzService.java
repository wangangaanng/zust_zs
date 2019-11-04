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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.dao.BckjBizXxpzDao;
import com.zghzbckj.manage.entity.BckjBizXxpz;
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
public class BckjBizXxpzService extends CrudService<BckjBizXxpzDao, BckjBizXxpz> {

    private static final Logger log = Logger.getLogger(BckjBizXxpzService.class);
    @Autowired
    BckjBizBmDao bckjBizBmDao;

    @Override
    public BckjBizXxpz get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizXxpz> findList(BckjBizXxpz bckjBizXxpz) {
        return super.findList(bckjBizXxpz);
    }

    @Override
    public PageInfo<BckjBizXxpz> findPage(Page<BckjBizXxpz> page, BckjBizXxpz bckjBizXxpz) {
        return super.findPage(page, bckjBizXxpz);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizXxpz bckjBizXxpz) {
        super.saveOrUpdate(bckjBizXxpz);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizXxpz bckjBizXxpz) {
        super.delete(bckjBizXxpz);
    }


    /**
     * <p>方法:findPagebckjBizXxpz TODO后台BckjBizXxpz分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.PageInfo  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public PageInfo<BckjBizXxpz> findPageBckjBizXxpz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizXxpz> page = findPage(dataMap, pageNo, pageSize, null);
        return page;
    }

    /**
     * <p>方法:savebckjBizXxpz TODO保存BckjBizXxpz信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizXxpz  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BckjBizXxpz saveBckjBizXxpz(Map<String, Object> mapData) {
        BckjBizXxpz bckjBizXxpz = JsonUtil.map2Bean(mapData, BckjBizXxpz.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizXxpz bckjBizXxpzIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizXxpz, bckjBizXxpzIndata);
            bckjBizXxpz = bckjBizXxpzIndata;
        }
        saveOrUpdate(bckjBizXxpz);
        return bckjBizXxpz;
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizXxpz </p>
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
            BckjBizXxpz bckjBizXxpz = new BckjBizXxpz();
            bckjBizXxpz.setOwid(owid);
            this.dao.delete(bckjBizXxpz);
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }

    /**
    *<p>方法:getXxxx TODO学校信息获取 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.entity.PageInfo  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/24 16:12  </li>
    *</ul>
    */
    public Page getXxxx(Map<String, Object> mapData) throws Exception {
        int pageNo= MapUtils.getInt(mapData,"pageNo");
        int pageSize= MapUtils.getInt(mapData,"pageSize");
        Page<Map> page = new Page(pageNo, pageSize);
        mapData.put("page", page);
        mapData.put("orderBy", "");
        page.setList(this.dao.findMapListByMap(mapData));
        String bmnd = DateUtil.getCurrentDate(CommonConstant.DATE_FROMART).substring(0, 4);
        mapData.put("bmnd", bmnd);
        for(Map one:page.getList()){
            mapData.put("xxbh",MapUtils.getString(one,"xxbh"));
            Map map= bckjBizBmDao.getBmxx(mapData);
            if(null!=map){
                BeanUtil.copyBean(map,one,"bmzy","xybCz","memo","jjly","bmState","applyOwid");
            }else{
                one.put("bmState",0);
            }
        }
        return page;
    }
}