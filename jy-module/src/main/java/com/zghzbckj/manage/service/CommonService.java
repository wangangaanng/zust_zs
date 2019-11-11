package com.zghzbckj.manage.service;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.MapUtils;

import com.zghzbckj.manage.dao.CommonDao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        int ytg = 0;
        int wtg = 0;
        for (Map<String, Object> qyxx : qyxxList) {
            objectMap = new HashMap<>();
            if ("2".equals(MapUtils.getString(qyxx, "state"))) {
                objectMap.put("name", "已通过企业");
                ytg = MapUtils.getInt(qyxx, "value");
                objectMap.put("value", ytg);
                //审核数
                result.put("shs", MapUtils.getInt(qyxx, "value"));
            } else {
                wtg = MapUtils.getInt(qyxx, "value");
                objectMap.put("value", wtg);
                objectMap.put("name", "未通过企业");
            }
            resultList.add(objectMap);
        }
        //饼图数据
        result.put("pieData", resultList);
        //企业数
        result.put("qys", ytg + wtg);
        //涉及行业
        result.put("sjhy", commonDao.getCompanyNum("qy_hylb", MapUtils.getString(dataMap, "year")).size());
        //涉及类型
        result.put("sjlx", commonDao.getCompanyNum("qy_gsxz", MapUtils.getString(dataMap, "year")).size());
        return result;
    }

    /**
     *<p>方法:职位按照工作职能统计职位数量 getJobPie TODO </p>
     *<ul>
     *<li> @param dataMap TODO</li>
     *<li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/10/24 15:50  </li>
     *</ul>
     */
    public Map<String, Object> getJobPie(Map<String, Object> dataMap) {
        List<Map<String, Object>> jobList = commonDao.getListJobNumber(dataMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = null;
        Map<String, Object> params = null;
        int zws = 0;
        for (Map<String, Object> job : jobList) {
            params = new HashMap<>();
            objectMap = new HashMap<>();
            params.put("dicType", 20003);
            params.put("dicVal1", MapUtils.getString(job, "gzzn"));
            objectMap.put("name", commonDao.getOneDic(params).get("DIC_VAL2"));
            objectMap.put("value", MapUtils.getInt(job, "value"));
            zws += MapUtils.getInt(job, "value");
            resultList.add(objectMap);
        }
        //饼图数据
        result.put("pieData", resultList);
        //职位数
        result.put("zws", zws);
        //涉及行业
        result.put("sjhy", jobList.size());
        //报名数，bmlx 1学生 bmdx 2职位
        result.put("bms", commonDao.getJobApplyNumber("1", "2", MapUtils.getString(dataMap, "year")));
        //关注数，gzlx 0职位 xxlb 0关注
        result.put("gzs", commonDao.getJobFollowNumber("0", "0", MapUtils.getString(dataMap, "year")));
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
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        //3招聘会 4宣讲会
        if ("3".equals(MapUtils.getString(dataMap, "zwlx"))) {
            //未举办
            Map<String, Object> wjbMap = commonDao.getMapWjbNumber(dataMap);
            wjbMap.put("name", "未举办招聘会");
            resultList.add(wjbMap);
            //已举办
            Map<String, Object> yjbMap = commonDao.getMapYjbNumber(dataMap);
            yjbMap.put("name", "已举办招聘会");
            resultList.add(yjbMap);
            //招聘会总数
            result.put("total", MapUtils.getInt(wjbMap, "value") + MapUtils.getInt(yjbMap, "value"));
            //未举办
            result.put("wjb", MapUtils.getInt(wjbMap, "value"));
            //学生数
            result.put("xss", commonDao.getJobApplyNumber("1", "0", MapUtils.getString(dataMap, "year")));
            //企业数
            result.put("qys", commonDao.getJobApplyNumber("0", "0", MapUtils.getString(dataMap, "year")));
            //招聘会饼图数据
            result.put("pieData", resultList);
        } else {
            //未举办
            Map<String, Object> wjbMap = commonDao.getMapWjbNumber(dataMap);
            wjbMap.put("name", "未举办宣讲会");
            resultList.add(wjbMap);
            //已举办
            Map<String, Object> yjbMap = commonDao.getMapYjbNumber(dataMap);
            yjbMap.put("name", "已举办宣讲会");
            resultList.add(yjbMap);
            //宣讲会总数
            result.put("total", MapUtils.getInt(wjbMap, "value") + MapUtils.getInt(yjbMap, "value"));
            //未举办
            result.put("wjb", MapUtils.getInt(wjbMap, "value"));
            //学生数
            result.put("xss", commonDao.getJobApplyNumber("1", "1", MapUtils.getString(dataMap, "year")));
            //企业数
            result.put("qys", commonDao.getJobApplyNumber("0", "1", MapUtils.getString(dataMap, "year")));
            //宣讲会饼图数据
            result.put("pieData", resultList);
        }
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
    public List<Map<String, Object>> getJobBar(Map<String, Object> dataMap) throws ParseException {
        //zwlx  0职位数 3招聘会数 4宣讲会数
        String[] zwlxList = {"0", "3", "4"};
        List<Map<String, Object>> resultList = new ArrayList<>(3);
        Map<String, Object> resultMap = null;
        List<Integer> dataList = null;
        for (String zwlx : zwlxList) {
            resultMap = new HashMap<>();
            dataList = listMonth(zwlx, MapUtils.getString(dataMap, "minDate"), MapUtils.getString(dataMap, "maxDate"));
            resultMap.put("type", "bar");
            resultMap.put("data", dataList);
            if ("0".equals(zwlx)) {
                resultMap.put("name", "职位数");
            } else if ("3".equals(zwlx)) {
                resultMap.put("name", "招聘会数");
            } else {
                resultMap.put("name", "宣讲会数");
            }
            resultList.add(resultMap);
        }
        return resultList;
    }

    /**
     *<p>方法:统计12个月招聘信息的数量 listMonth TODO </p>
     *<ul>
     *<li> @param zwlx TODO</li>
     *<li>@return java.util.List<java.lang.Integer>  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/10/23 13:48  </li>
     *</ul>
     */
    private List<Integer> listMonth(String zwlx, String minDate, String maxDate) throws ParseException {
        List<Integer> result = new ArrayList<>(12);
        List<String> months = getMonthBetweenDates(minDate, maxDate);
        System.out.println(months);
        for (String date : months) {
            if ("0".equals(zwlx)) {
                //职位根据创建时间
                result.add(commonDao.getJobNumberByCreatetime(zwlx, date));
            } else {
                //招聘会、宣讲会根据开始日期
                result.add(commonDao.getJobNumberByZphKsrq(zwlx, date));
            }
        }
        return result;
    }

    /**
     *<p>功能描述:时间段之间内的月份列表 getMonthBetweenDates</p >
     *<ul>
     *<li>@param [minDate, maxDate]</li>
     *<li>@return java.util.List<java.lang.String></li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/11 16:36</li>
     *</ul>
     */
    public List<String> getMonthBetweenDates(String minDate, String maxDate) throws ParseException {
        List<String> result = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(formatter.parse(minDate));
        max.setTime(formatter.parse(maxDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        while (min.before(max)) {
            result.add(formatter.format(min.getTime()));
            min.add(Calendar.MONTH, 1);
        }
        return result;
    }

}