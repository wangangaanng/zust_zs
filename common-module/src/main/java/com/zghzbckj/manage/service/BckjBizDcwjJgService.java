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
import com.zghzbckj.manage.dao.BckjBizDcwjDao;
import com.zghzbckj.manage.dao.BckjBizDcwjDtmxDao;
import com.zghzbckj.manage.dao.BckjBizDcwjJgDao;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;
import com.zghzbckj.manage.entity.BckjBizDcwj;
import com.zghzbckj.manage.entity.BckjBizDcwjDtmx;
import com.zghzbckj.manage.entity.BckjBizDcwjJg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BckjBizDcwjJgService extends CrudService<BckjBizDcwjJgDao, BckjBizDcwjJg> {

    private static final Logger log = Logger.getLogger(BckjBizDcwjJgService.class);

    @Autowired
    BckjBizDcwjDao bckjBizDcwjDao;
    @Autowired
    BckjBizDcwjDtmxDao bckjBizDcwjDtmxDao;

    @Override
    public BckjBizDcwjJg get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizDcwjJg> findList(BckjBizDcwjJg bckjBizDcwjJg) {
        return super.findList(bckjBizDcwjJg);
    }

    @Override
    public PageInfo<BckjBizDcwjJg> findPage(Page<BckjBizDcwjJg> page, BckjBizDcwjJg bckjBizDcwjJg) {
        return super.findPage(page, bckjBizDcwjJg);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizDcwjJg bckjBizDcwjJg) {
        super.saveOrUpdate(bckjBizDcwjJg);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizDcwjJg bckjBizDcwjJg) {
        super.delete(bckjBizDcwjJg);
    }

    /**
     *<p>方法:查询调查人数 countPeople TODO </p>
     *<ul>
     *<li> @param dcwjRefOwid TODO</li>
     *<li>@return int  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/9/25 15:54  </li>
     *</ul>
     */
    public int countPeople(String dcwjRefOwid) {
        return this.dao.countPeople(dcwjRefOwid);
    }

    public PageInfo<Map<String, Object>> listResult(Map<String, Object> dataMap, Integer pageNo, Integer pageSize) {
        PageInfo<BckjBizDcwjJg> jgListPage = findPage(dataMap, pageNo, pageSize, "createtime");
        if (TextUtils.isEmpty(jgListPage.getRecords()) || jgListPage.getRecords().size() <= 0) {
            return null;
        }
        List<BckjBizDcwjJg> jgList = jgListPage.getRecords();
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> objectMap = null;
        List<BckjBizDcwjDtmx> dtmxList = null;
        for (BckjBizDcwjJg jg : jgList) {
            objectMap = new HashMap<>();
            if (TextUtils.isEmpty(jg.getDtrxm())) {
                objectMap.put("dtrxm", "匿名用户");
            } else {
                objectMap.put("dtrxm", jg.getDtrxm());
            }
            objectMap.put("ksdt", jg.getKsdt());
            objectMap.put("jsdt", jg.getKsdt());
            objectMap.put("dtsc", jg.getDtsc());
            dtmxList = bckjBizDcwjDtmxDao.listDtmx(jg.getOwid());
            for (BckjBizDcwjDtmx dtmx : dtmxList) {
                objectMap.put(dtmx.getDcwjtmRefOwid(), dtmx.getWjda());
            }
            dataList.add(objectMap);
        }
        //统计行
        Map<String, Object> statsMap = new HashMap<>();
        statsMap.put("dtrxm", "总计：共有" + jgList.size() + "个用户参加问卷调查");
        dataList.add(0, statsMap);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        pageInfo.setRecords(dataList);
        pageInfo.setCurrentIndex(jgListPage.getCurrentIndex());
        pageInfo.setCurrentIndex(jgListPage.getCurrentIndex());
        pageInfo.setTotalCount(jgListPage.getTotalCount());
        pageInfo.setTotalPage(jgListPage.getTotalPage());
        pageInfo.setPageSize(jgListPage.getPageSize());
        return pageInfo;
    }

    /**
     * <p>方法:findPagebckjBizDcwjJg TODO后台BckjBizDcwjJg分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizDcwjJg(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizDcwjJg> page = findPage(dataMap, pageNo, pageSize, null);
        List<BckjBizDcwjJg> records = page.getRecords();
        //统计行
        BckjBizDcwjJg jg = new BckjBizDcwjJg();
        jg.setDtrxm("共有：" + page.getTotalCount() + "个人参加问卷调查");
        jg.setReadOnly(true);
        records.add(0, jg);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizDcwjJg TODO保存BckjBizDcwjJg信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizDcwjJg(Map<String, Object> mapData) {
        BckjBizDcwjJg bckjBizDcwjJg = JsonUtil.map2Bean(mapData, BckjBizDcwjJg.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizDcwjJg bckjBizDcwjJgIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwjJg, bckjBizDcwjJgIndata);
            bckjBizDcwjJg = bckjBizDcwjJgIndata;
        }
        saveOrUpdate(bckjBizDcwjJg);
        return ResponseMessage.sendOK(bckjBizDcwjJg);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizDcwjJg </p>
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
            BckjBizDcwjJg bckjBizDcwjJg = new BckjBizDcwjJg();
            bckjBizDcwjJg.setOwid(owid);
            this.dao.delete(bckjBizDcwjJg);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

}