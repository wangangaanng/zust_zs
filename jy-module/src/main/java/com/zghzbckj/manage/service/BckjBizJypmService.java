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
import com.zghzbckj.manage.dao.BckjBizJypmDao;
import com.zghzbckj.manage.entity.BckjBizJypm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJypmService extends CrudService<BckjBizJypmDao, BckjBizJypm> {

    private static final Logger log = Logger.getLogger(BckjBizJypmService.class);

    @Autowired
    BckjBizJypmDao bckjBizJypmDao;

    @Override
    public BckjBizJypm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJypm> findList(BckjBizJypm bckjBizJypm) {
        return super.findList(bckjBizJypm);
    }

    @Override
    public PageInfo<BckjBizJypm> findPage(Page<BckjBizJypm> page, BckjBizJypm bckjBizJypm) {
        return super.findPage(page, bckjBizJypm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJypm bckjBizJypm) {
        super.saveOrUpdate(bckjBizJypm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJypm bckjBizJypm) {
        super.delete(bckjBizJypm);
    }

    /**
     * <p>方法:就业排行榜列表 listRank TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/12 11:30  </li>
     * </ul>
     */
    public List<Map<String, Object>> listRank(Map<String, Object> dataMap) {
        //统计学院就业情况
        List<Map<String, Object>> collegeList = this.dao.collegeStats(MapUtils.getString(dataMap, "pmnf"));
        for (Map<String, Object> college : collegeList) {
            List<Map<String, Object>> majorList = this.dao.majorList(MapUtils.getString(college, "szxy"), MapUtils.getString(college, "pmnf"));
            Map<String, Object> statsMap = new HashMap<>();
            BigDecimal pmbyzrs = new BigDecimal(MapUtils.getInt(college, "pmbyrs"));
            BigDecimal pmqyzrs = new BigDecimal(MapUtils.getInt(college, "pmqyrs") * 100);
            statsMap.put("pmzy", "合计");
            statsMap.put("pmbyrs", college.get("pmbyrs"));
            statsMap.put("pmqyrs", college.get("pmqyrs"));
            statsMap.put("pmjyl", pmqyzrs.divide(pmbyzrs, 2, RoundingMode.HALF_UP));
            college.put("avgjyl", pmqyzrs.divide(pmbyzrs, 2, RoundingMode.HALF_UP));
            majorList.add(statsMap);
            college.put("pmzyList", majorList);
        }
        //根据就业率排名
        collegeList.sort((o1, o2) -> {
            BigDecimal a = (BigDecimal) o1.get("avgjyl");
            BigDecimal b = (BigDecimal) o2.get("avgjyl");
            if (a.compareTo(b) > -1) {
                return -1;
            } else if (a.compareTo(b) == 0) {
                return 0;
            } else {
                return 1;
            }
        });
        return collegeList;
    }

    /**
     * <p>方法:根据学院专业名称查询 getByCollegeMajor TODO </p>
     * <ul>
     * <li> @param majorName TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizJypm  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/22 18:14  </li>
     * </ul>
     */
    public BckjBizJypm getByCollegeMajor(String collegeName, String majorName, String pmnf) {
        return this.dao.getByMajor(collegeName, majorName, pmnf);
    }

    /**
     * <p>方法:findPagebckjBizJypm TODO后台BckjBizJypm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJypm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizJypm> page = findPage(dataMap, pageNo, pageSize, "pmjyl");
        List<BckjBizJypm> records = page.getRecords();
        //统计行
        BckjBizJypm jypm = new BckjBizJypm();
        jypm.setSzxy("共有："+ page.getTotalCount() +"个专业");
        jypm.setReadOnly(true);
        records.add(0, jypm);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizJypm TODO保存BckjBizJypm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJypm(Map<String, Object> mapData) {
        BckjBizJypm bckjBizJypm = JsonUtil.map2Bean(mapData, BckjBizJypm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJypm bckjBizJypmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJypm, bckjBizJypmIndata);
            bckjBizJypm = bckjBizJypmIndata;
        }
        saveOrUpdate(bckjBizJypm);
        return ResponseMessage.sendOK(bckjBizJypm);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJypm </p>
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
            BckjBizJypm bckjBizJypm = new BckjBizJypm();
            bckjBizJypm.setOwid(owid);
            this.dao.delete(bckjBizJypm);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

}