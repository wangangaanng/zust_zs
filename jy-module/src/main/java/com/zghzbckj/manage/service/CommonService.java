package com.zghzbckj.manage.service;

import com.ourway.base.utils.MapUtils;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <dl>
 * <dt>CommonService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@Service("commonService")
public class CommonService {

    @Autowired
    CommonDao commonDao;

    /**
     * <p>方法:查询待举办招聘会、宣讲会 listJob TODO </p>
     * <ul>
     * <li> @param dataMap 职位类型3招聘会4宣讲会 TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/22 10:02  </li>
     * </ul>
     */
    public List<Map<String, Object>> listJob(Map<String, Object> dataMap) {
        return commonDao.getListJob(dataMap);
    }

    /**
     * <p>方法:查询待审核的企业 listCompany TODO </p>
     * <ul>
     * <li> @param dataMap 1待审核 TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/22 10:22  </li>
     * </ul>
     */
    public List<Map<String, Object>> listCompany(Map<String, Object> dataMap) {
        dataMap.put("state", 1);
        return commonDao.getListCompany(dataMap);
    }

    /**
     * <p>方法:统计企业审核通过与未通过数量 getCompanyPie TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/22 13:52  </li>
     * </ul>
     */
    public Map<String, Object> getCompanyPie(Map<String, Object> dataMap) {
        List<Map<String, Object>> qyxxList = commonDao.getListCompanyNumber(dataMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = null;
        for (Map<String, Object> qyxx : qyxxList) {
            objectMap = new HashMap<>();
            if ("2".equals(MapUtils.getString(qyxx, "state"))) {
                objectMap.put("name", "已通过企业");
                objectMap.put("value", MapUtils.getInt(qyxx, "value"));
                //审核数
                result.put("shs", MapUtils.getInt(qyxx, "value"));
            } else {
                objectMap.put("value", MapUtils.getInt(qyxx, "value"));
                objectMap.put("name", "未通过企业");
            }
            resultList.add(objectMap);
        }
        //饼图数据
        result.put("barData", resultList);
        //企业数
        result.put("qys", MapUtils.getInt(qyxxList.get(0), "value") + MapUtils.getInt(qyxxList.get(1), "value"));
        //涉及行业
        result.put("sjhy", commonDao.getCompanyNum("qy_hylb").size());
        //涉及类型
        result.put("sjlx", commonDao.getCompanyNum("qy_gsxz").size());
        return result;
    }

    public Map<String, Object> getJobPie(Map<String, Object> dataMap) {
        List<Map<String, Object>> jobList = commonDao.getListJobNumber(dataMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = null;
        int zws = 0;
        int gzs = 0;
        int bms = 0;
        for (Map<String, Object> job : jobList) {
            objectMap = new HashMap<>();
            objectMap.put("name", MapUtils.getString(job, "zwbt"));
            objectMap.put("value", MapUtils.getInt(job, "value"));
            zws += MapUtils.getInt(job, "value");
            gzs += MapUtils.getInt(job, "gzs");
            resultList.add(objectMap);
        }
        //饼图数据
        result.put("barData", resultList);
        //涉及行业
        result.put("sjhy", resultList.size());
        //职位数
        result.put("zws", zws);
        //关注数
        result.put("gzs", gzs);
        //报名数
        result.put("bms", bms);
        return result;
    }

    /**
     * <p>方法:统计招聘会、宣讲会举办与未举办饼图 getMeetingPie TODO </p>
     * <ul>
     * <li> @param dataMap 职位类型3招聘会4宣讲会 TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/22 14:51  </li>
     * </ul>
     */
    public Map<String, Object> getMeetingPie(Map<String, Object> dataMap) {
        List<Map<String, Object>> meetingList = commonDao.getListJobNumber(dataMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = null;
        return result;
    }
    
    /**
     *<p>方法:统计招聘会、职位数、宣讲会数柱状图 getJobBar TODO </p>
     *<ul>
     *<li> @param dataMap TODO</li>
     *<li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/10/22 15:25  </li>
     *</ul>
     */
    public List<Map<String, Object>> getJobBar(Map<String, Object> dataMap) {
        List<Map<String, Object>> resultList = listMonth();
        return resultList;
    }

    private List<Map<String, Object>> listMonth() {
        List<Map<String, Object>> result = new ArrayList<>(12);
        Map<String, Object> monthMap = null;
        for (int i = 1; i <= 12; i++) {
            monthMap = new HashMap<>();
            monthMap.put("month", i + "月");
            result.add(monthMap);
        }
        return result;
    }


}