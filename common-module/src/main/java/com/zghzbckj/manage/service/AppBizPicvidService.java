/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.dao.AppBizPicvidDao;
import com.zghzbckj.manage.entity.AppBizPicvid;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-04-13
 */
@Service
@Transactional(readOnly = true)
public class AppBizPicvidService extends CrudService<AppBizPicvidDao, AppBizPicvid> {

    private static final Logger log = Logger.getLogger(AppBizPicvidService.class);

    @Override
    public AppBizPicvid get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<AppBizPicvid> findList(AppBizPicvid appBizPicvid) {
        return super.findList(appBizPicvid);
    }

    @Override
    public PageInfo<AppBizPicvid> findPage(Page<AppBizPicvid> page, AppBizPicvid appBizPicvid) {
        return super.findPage(page, appBizPicvid);
    }

    @Transactional(readOnly = false)
    public void save(AppBizPicvid appBizPicvid) {
        super.saveOrUpdate(appBizPicvid);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(AppBizPicvid appBizPicvid) {
        super.delete(appBizPicvid);
    }


    /**
     * <p>方法:findPageappBizPicvid TODO后台AppBizPicvid分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageAppBizPicvid(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<AppBizPicvid> page = findPage(dataMap, pageNo, pageSize, " a.djs ");
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:saveappBizPicvid TODO保存AppBizPicvid信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveAppBizPicvid(Map<String, Object> mapData) {
        AppBizPicvid appBizPicvid = JsonUtil.map2Bean(mapData, AppBizPicvid.class);
        if (!TextUtils.isEmpty(appBizPicvid.getOwid())) {
            AppBizPicvid appBizPicvidIndata = get(appBizPicvid.getOwid());
            BeanUtil.copyPropertiesIgnoreNull(appBizPicvid, appBizPicvidIndata);
            appBizPicvid = appBizPicvidIndata;
        }else{
            appBizPicvid.setLmbt(CacheUtil.getVal(CommonModuleContant.LMBH+appBizPicvid.getLmbh()));
            appBizPicvid.setLmbt2(CacheUtil.getVal(CommonModuleContant.LMBH2+appBizPicvid.getLmbh2()));
        }
        saveOrUpdate(appBizPicvid);
        return ResponseMessage.sendOK(appBizPicvid);
    }

    /**
     * <p>方法:removeOrder TODO多条删除AppBizPicvid </p>
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
            AppBizPicvid appBizPicvid = new AppBizPicvid();
            appBizPicvid.setOwid(owid);
            this.dao.delete(appBizPicvid);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public List<AppBizPicvid> getShowList() {
        Map<String,Object> param= Maps.newHashMap();
        param.put("lx",1);
        param.put("orderBy"," a.djs ");
        return this.dao.findListByMap(param);
    }
}