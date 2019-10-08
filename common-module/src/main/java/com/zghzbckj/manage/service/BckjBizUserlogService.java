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
import com.zghzbckj.manage.dao.BckjBizUserlogDao;
import com.zghzbckj.manage.entity.BckjBizUserlog;
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
 * @version 2019-10-08
 */
@Service
@Transactional(readOnly = true)
public class BckjBizUserlogService extends CrudService<BckjBizUserlogDao, BckjBizUserlog> {

    private static final Logger log = Logger.getLogger(BckjBizUserlogService.class);

    @Override
    public BckjBizUserlog get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizUserlog> findList(BckjBizUserlog bckjBizUserlog) {
        return super.findList(bckjBizUserlog);
    }

    @Override
    public PageInfo<BckjBizUserlog> findPage(Page<BckjBizUserlog> page, BckjBizUserlog bckjBizUserlog) {
        return super.findPage(page, bckjBizUserlog);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizUserlog bckjBizUserlog) {
        super.saveOrUpdate(bckjBizUserlog);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizUserlog bckjBizUserlog) {
        super.delete(bckjBizUserlog);
    }


    /**
     * <p>方法:findPagebckjBizUserlog TODO后台BckjBizUserlog分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizUserlog(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizUserlog> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizUserlog TODO保存BckjBizUserlog信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizUserlog(Map<String, Object> mapData) {
        BckjBizUserlog bckjBizUserlog = JsonUtil.map2Bean(mapData, BckjBizUserlog.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizUserlog bckjBizUserlogIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizUserlog, bckjBizUserlogIndata);
            bckjBizUserlog = bckjBizUserlogIndata;
        }
        saveOrUpdate(bckjBizUserlog);
        return ResponseMessage.sendOK(bckjBizUserlog);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizUserlog </p>
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
            BckjBizUserlog bckjBizUserlog = new BckjBizUserlog();
            bckjBizUserlog.setOwid(owid);
            this.dao.delete(bckjBizUserlog);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

}