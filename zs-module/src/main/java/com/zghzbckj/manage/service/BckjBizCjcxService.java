/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizCjcxDao;
import com.zghzbckj.manage.entity.BckjBizCjcx;
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
public class BckjBizCjcxService extends CrudService<BckjBizCjcxDao, BckjBizCjcx> {

    private static final Logger log = Logger.getLogger(BckjBizCjcxService.class);

    @Override
    public BckjBizCjcx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizCjcx> findList(BckjBizCjcx bckjBizCjcx) {
        return super.findList(bckjBizCjcx);
    }

    @Override
    public PageInfo<BckjBizCjcx> findPage(Page<BckjBizCjcx> page, BckjBizCjcx bckjBizCjcx) {
        return super.findPage(page, bckjBizCjcx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizCjcx bckjBizCjcx) {
        super.saveOrUpdate(bckjBizCjcx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizCjcx bckjBizCjcx) {
        super.delete(bckjBizCjcx);
    }


    /**
     * <p>方法:findPagebckjBizCjcx TODO后台BckjBizCjcx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizCjcx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizCjcx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizCjcx TODO保存BckjBizCjcx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizCjcx(Map<String, Object> mapData) {
        BckjBizCjcx bckjBizCjcx = JsonUtil.map2Bean(mapData, BckjBizCjcx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizCjcx bckjBizCjcxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizCjcx, bckjBizCjcxIndata);
            bckjBizCjcx = bckjBizCjcxIndata;
        }
        saveOrUpdate(bckjBizCjcx);
        return ResponseMessage.sendOK(bckjBizCjcx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizCjcx </p>
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
            BckjBizCjcx bckjBizCjcx = new BckjBizCjcx();
            bckjBizCjcx.setOwid(owid);
            this.dao.delete(bckjBizCjcx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
    *<p>方法:getCjxx TODO查询成绩 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.manage.entity.BckjBizCjcx  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/15 13:54  </li>
    *</ul>
    */
    public BckjBizCjcx getCjxx(Map<String, Object> mapData) {
        List<BckjBizCjcx> list = this.dao.findListByMap(mapData);
        if(null!=list && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}