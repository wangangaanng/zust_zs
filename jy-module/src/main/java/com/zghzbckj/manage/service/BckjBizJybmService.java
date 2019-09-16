/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.entity.BckjBizJybm;
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
public class BckjBizJybmService extends CrudService<BckjBizJybmDao, BckjBizJybm> {

    private static final Logger log = Logger.getLogger(BckjBizJybmService.class);

    @Override
    public BckjBizJybm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJybm> findList(BckjBizJybm bckjBizJybm) {
        return super.findList(bckjBizJybm);
    }

    @Override
    public PageInfo<BckjBizJybm> findPage(Page<BckjBizJybm> page, BckjBizJybm bckjBizJybm) {
        return super.findPage(page, bckjBizJybm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJybm bckjBizJybm) {
        super.saveOrUpdate(bckjBizJybm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJybm bckjBizJybm) {
        super.delete(bckjBizJybm);
    }


    /**
     * <p>方法:findPagebckjBizJybm TODO后台BckjBizJybm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJybm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizJybm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizJybm TODO保存BckjBizJybm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJybm(Map<String, Object> mapData) {
        BckjBizJybm bckjBizJybm = JsonUtil.map2Bean(mapData, BckjBizJybm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJybm bckjBizJybmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJybm, bckjBizJybmIndata);
            bckjBizJybm = bckjBizJybmIndata;
        }
        saveOrUpdate(bckjBizJybm);
        return ResponseMessage.sendOK(bckjBizJybm);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJybm </p>
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
            BckjBizJybm bckjBizJybm = new BckjBizJybm();
            bckjBizJybm.setOwid(owid);
            this.dao.delete(bckjBizJybm);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public Map applyJob(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizJybm jybm = new BckjBizJybm();
        try {
            jybm = MapUtils.map2Bean(mapData, BckjBizJybm.class);
            //待审核
            jybm.setState(JyContant.JOB_ZT_DSH);
            saveOrUpdate(jybm);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", jybm);
        return resultMap;
    }

    public PageInfo<BckjBizJybm> myBmList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizJybm> page = new Page<>(pageNo, pageSize);

        List<BckjBizJybm> bmList = this.dao.findListByMap(dataMap);
        page.setList(bmList);
        PageInfo<BckjBizJybm> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }

    public BckjBizJybm getOneByJobHy(Map<String, Object> datamap) {
       return this.dao.getOneByJobHy(datamap);
    }
}