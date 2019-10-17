/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.*;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizDcwjDtmxDao;
import com.zghzbckj.manage.entity.BckjBizDcwjDtmx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ourway.base.utils.BeanUtil;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDcwjTmService extends CrudService<BckjBizDcwjTmDao, BckjBizDcwjTm> {

    private static final Logger log = Logger.getLogger(BckjBizDcwjTmService.class);

    @Autowired
    BckjBizDcwjDtmxDao bckjBizDcwjDtmxDao;

    @Override
    public BckjBizDcwjTm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizDcwjTm> findList(BckjBizDcwjTm bckjBizDcwjTm) {
        return super.findList(bckjBizDcwjTm);
    }

    @Override
    public PageInfo<BckjBizDcwjTm> findPage(Page<BckjBizDcwjTm> page, BckjBizDcwjTm bckjBizDcwjTm) {
        return super.findPage(page, bckjBizDcwjTm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizDcwjTm bckjBizDcwjTm) {
        super.saveOrUpdate(bckjBizDcwjTm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizDcwjTm bckjBizDcwjTm) {
        super.delete(bckjBizDcwjTm);
    }

    /**
     * <p>方法:调查结果统计 findQuestionResultList TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.entity.PageInfo<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/17 14:32  </li>
     * </ul>
     */
    public PageInfo<Map<String, Object>> findQuestionResultList(Map<String, Object> dataMap, Integer pageNo, Integer pageSize) {
        //根据题目顺序列出所有题目
        PageInfo<BckjBizDcwjTm> tmPageInfo = findPage(dataMap, pageNo, pageSize, "tmsx");
        if (TextUtils.isEmpty(tmPageInfo.getRecords()) || tmPageInfo.getRecords().size() <= 0) {
            return null;
        }
        BckjBizDcwjTm tmStats = countTotalPeople(MapUtils.getString(dataMap, "dcwjRefOwid"));
        List<BckjBizDcwjTm> tmList = tmPageInfo.getRecords();
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map = null;
        for (BckjBizDcwjTm tm : tmList) {
            map = new HashMap<>();
            map.put("tmmc", tm.getTmmc());
            map.put("tmlx", tm.getTmlx());
            //循环A~Z
            for (char i = 'A'; i <= 'Z'; i++) {
                map.put("tmxx" + i, bckjBizDcwjDtmxDao.countDtmx(tm.getOwid(), i));
            }
            dataList.add(map);
        }
        Map<String, Object> statsMap = new HashMap<>();
        statsMap.put("tmmc", tmStats.getTmmc());
        dataList.add(0, statsMap);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        pageInfo.setRecords(dataList);
        pageInfo.setPageSize(tmPageInfo.getPageSize());
        pageInfo.setTotalPage(tmPageInfo.getTotalPage());
        pageInfo.setTotalCount(tmPageInfo.getTotalCount());
        pageInfo.setCurrentIndex(tmPageInfo.getCurrentIndex());
        pageInfo.setCurrentPage(tmPageInfo.getCurrentPage());
        return pageInfo;
    }

    /**
     *<p>方法:计算总的参与调查人数 countTotalPeople TODO </p>
     *<ul>
     *<li> @param dcwjRefOwid TODO</li>
     *<li>@return com.zghzbckj.manage.entity.BckjBizDcwjTm  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/10/17 16:42  </li>
     *</ul>
     */
    private BckjBizDcwjTm countTotalPeople(String dcwjRefOwid) {
        BckjBizDcwjTm tm = new BckjBizDcwjTm();
        List<BckjBizDcwjDtmx> dtmxList = bckjBizDcwjDtmxDao.listDtmxByDcwj(dcwjRefOwid);
        if (TextUtils.isEmpty(dtmxList) || dtmxList.size() <= 0) {
            tm.setTmmc("合计：0个用户已参加问卷调查");
        } else {
            tm.setTmmc("合计：共有" + dtmxList.size() + "个用户参加问卷调查");
        }
        tm.setReadOnly(true);
        return tm;
    }

    /**
     * <p>方法:findPagebckjBizDcwjTm TODO后台BckjBizDcwjTm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizDcwjTm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizDcwjTm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizDcwjTm TODO保存BckjBizDcwjTm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizDcwjTm(Map<String, Object> mapData) {
        BckjBizDcwjTm bckjBizDcwjTm = JsonUtil.map2Bean(mapData, BckjBizDcwjTm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizDcwjTm bckjBizDcwjTmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwjTm, bckjBizDcwjTmIndata);
            bckjBizDcwjTm = bckjBizDcwjTmIndata;
        }
        saveOrUpdate(bckjBizDcwjTm);
        return ResponseMessage.sendOK(bckjBizDcwjTm);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizDcwjTm </p>
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
            BckjBizDcwjTm bckjBizDcwjTm = new BckjBizDcwjTm();
            bckjBizDcwjTm.setOwid(owid);
            this.dao.delete(bckjBizDcwjTm);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

}