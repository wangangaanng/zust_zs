/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizLntjDao;
import com.zghzbckj.manage.entity.BckjBizLntj;
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
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizLntjService extends CrudService<BckjBizLntjDao, BckjBizLntj> {

    private static final Logger log = Logger.getLogger(BckjBizLntjService.class);

    @Override
    public BckjBizLntj get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizLntj> findList(BckjBizLntj bckjBizLntj) {
        return super.findList(bckjBizLntj);
    }

    @Override
    public PageInfo<BckjBizLntj> findPage(Page<BckjBizLntj> page, BckjBizLntj bckjBizLntj) {
        return super.findPage(page, bckjBizLntj);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizLntj bckjBizLntj) {
        super.saveOrUpdate(bckjBizLntj);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizLntj bckjBizLntj) {
        super.delete(bckjBizLntj);
    }


    /**
     * <p>方法:findPagebckjBizLntj TODO后台BckjBizLntj分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizLntj(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizLntj> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizLntj TODO保存BckjBizLntj信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizLntj(Map<String, Object> mapData) {
        BckjBizLntj bckjBizLntj = JsonUtil.map2Bean(mapData, BckjBizLntj.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizLntj bckjBizLntjIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizLntj, bckjBizLntjIndata);
            bckjBizLntj = bckjBizLntjIndata;
        }
        saveOrUpdate(bckjBizLntj);
        return ResponseMessage.sendOK(bckjBizLntj);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizLntj </p>
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
            BckjBizLntj bckjBizLntj = new BckjBizLntj();
            bckjBizLntj.setOwid(owid);
            this.dao.delete(bckjBizLntj);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
     *<p>方法:getChanges TODO查询条件数据获取</p>
     *<ul>
     *<li> @param mapData TODO</li>
     *<li>@return java.util.List<com.zghzbckj.manage.entity.BckjBizLntj>  </li>
     *<li>@author D.chen.g </li>
     *<li>@date 2019/10/14 15:33  </li>
     *</ul>
     */
    public Map<String, Object> getChanges(Map<String, Object> mapData) {
        Map<String, Object> result = new HashMap<>();
        List<BckjBizLntj> nfList = this.dao.findListByNf(MapUtils.getString(mapData, "nf"));
        List<BckjBizLntj> sfList = this.dao.findListBySf(MapUtils.getString(mapData, "sf"));
        List<BckjBizLntj> klList = this.dao.findListByKl(MapUtils.getString(mapData, "kl"));
        List<BckjBizLntj> pcList = this.dao.findListByPc(MapUtils.getString(mapData, "pc"));
        List<BckjBizLntj> zyList = this.dao.findListByZy(MapUtils.getString(mapData, "zy"));
        result.put("nfList", nfList);
        result.put("sfList", sfList);
        result.put("klList", klList);
        result.put("pcList", pcList);
        result.put("zyList", zyList);
        return result;
    }

    /**
    *<p>方法:getResult TODO分页获取查询结果 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.base.entity.PageInfo<com.zghzbckj.manage.entity.BckjBizLntj>  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/14 16:00  </li>
    *</ul>
    */
    public PageInfo<BckjBizLntj> getResult(Map<String, Object> mapData) {
        Integer pageNo= MapUtils.getInt(mapData,"pageNo");
        Integer pageSize= MapUtils.getInt(mapData,"pageSize");
        PageInfo<BckjBizLntj> page = findPage(mapData, pageNo, pageSize, " a.createtime DESC");
        return page;
    }
}