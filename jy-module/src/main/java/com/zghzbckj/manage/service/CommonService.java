package com.zghzbckj.manage.service;

import com.ourway.base.utils.MapUtils;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.util.MapUtil;
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
     * <p>方法:查询企业数、职位数等 countNumber TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/12 14:36  </li>
     * </ul>
     */
    public Map<String, Object> countNumber(Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>(4);
        //企业数量
        result.put("companyNumber", commonDao.getCompanyNum(dataMap));
        //职位数量
        dataMap.put("zwlx", "0");
        result.put("positionNumber", commonDao.getZwlxNumber(dataMap));
        //宣讲会数量
        dataMap.put("zwlx", "4");
        result.put("meetNumber", commonDao.getZwlxNumber(dataMap));
        //招聘会数量
        dataMap.put("zwlx", "3");
        result.put("fairNumber", commonDao.getZwlxNumber(dataMap));
        return result;
    }

    /**
     * <p>方法:生成饼状图 generatePieCharts TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/12 16:18  </li>
     * </ul>
     */
    public Map<String, Object> generatePieCharts(Map<String, Object> dataMap) {
        //查询字典表
        List<Map<String, Object>> dataList = commonDao.listDicVal(dataMap);
        //新建饼状图
        List<String> nameList = new ArrayList<>(dataList.size());
        List<Map<String, Object>> pieList = new ArrayList<>(dataList.size());
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = null;
        for (Map<String, Object> data : dataList) {
            nameList.add(MapUtils.getString(data, "DIC_VAL2"));
            if ("1".equals(MapUtils.getString(dataMap, "type"))) {
                dataMap.put("qyHylb", MapUtils.getString(data, "DIC_VAL1"));
            } else {
                dataMap.put("qyGsgm", MapUtils.getString(data, "DIC_VAL2"));
            }
            objectMap = new HashMap<>(2);
            objectMap.put("name", MapUtils.getString(data, "DIC_VAL2"));
            objectMap.put("value", commonDao.getCompanyNum(dataMap));
            pieList.add(objectMap);
        }
        result.put("name", nameList);
        result.put("data", pieList);
        return result;
    }

    /**
     * <p>方法:企业地区 getCompanyArea TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/12 17:39  </li>
     * </ul>
     */
    public Map<String, Object> getCompanyArea(String year) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> dataList = commonDao.getCompanyArea(year);
        List<String> yAxisList = new ArrayList<>();
        List<String> seriesDataList = new ArrayList<>();
        for (Map<String, Object> data : dataList) {
            yAxisList.add(MapUtils.getString(data, "province"));
            seriesDataList.add(MapUtils.getString(data, "companyNumber"));
        }
        result.put("name", year + "年");
        result.put("yAxisData", yAxisList);
        result.put("seriesData", seriesDataList);
        return result;
    }

    /**
     * <p>方法:12个月企业注册统计 getCompanyReg TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/13 16:22  </li>
     * </ul>
     */
    public Map<String, Object> getCompanyReg(Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>();
        dataMap.put("groupBy", "createtime");
        //折线图y轴数据
        List<Integer> data = new ArrayList<>(12);
        for (int i = 1; i <= 12; i++) {
            dataMap.put("month", i);
            data.add(commonDao.getCompanyNum(dataMap));
        }
        result.put("yData", data);
        return result;
    }

    /**
     * <p>方法:职来职往等折线图列表 getLineChart TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/13 16:47  </li>
     * </ul>
     */
    public Map<String, Object> getLineChart(Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>();
        List<Integer> zwlxList = new ArrayList<>();
        result.put("name", MapUtils.getString(dataMap, "zwlxmc"));
        for (int i = 0; i <= 12; i++) {
            dataMap.put("month", i);
            zwlxList.add(commonDao.getZwlxNumber(dataMap));
        }
        result.put("data", zwlxList);
        return result;
    }

    /**
     * <p>方法:受关注职位、企业柱状图统计 getBarChart TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/14 11:37  </li>
     * </ul>
     */
    public Map<String, Object> getBarChart(Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>();
        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();
        List<Map<String, Object>> dataList = commonDao.getStudentFollow(dataMap);
        for (Map<String, Object> map : dataList) {
            if ("0".equals(MapUtils.getString(dataMap, "type"))) {
                xData.add(MapUtils.getString(map, "zwbt"));
            } else {
                xData.add(MapUtils.getString(map, "exp1"));
            }
            yData.add(MapUtils.getString(map, "jobNumber"));
        }
        result.put("xData", xData);
        result.put("yData", yData);
        return result;
    }

    /**
     * <p>方法:生源地列表 getMapList TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/15 19:37  </li>
     * </ul>
     */
    public List<Map<String, Object>> getMapList(Map<String, Object> dataMap) {
        List<BckjBizSyb> list = commonDao.getMapList(dataMap);
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> objectMap = null;
        for (int i = 0; i < JyContant.provinceList.length; i++) {
            objectMap = new HashMap<>();
            objectMap.put("name", JyContant.provinceList[i]);
            objectMap.put("value", 0);
            mapList.add(objectMap);
        }
        for (BckjBizSyb data : list) {
            for (int i = 0; i < JyContant.provinceList.length; i++) {
                Map<String, Object> map = new HashMap<>();
                if (data.getSyd().contains(JyContant.provinceList[i])) {
                    for (Map<String, Object> map1 : mapList) {
                        if (!(JyContant.provinceList[i].equals(map1.get("name").toString()))) {
                            continue;
                        } else {
                            map1.put("value", Integer.parseInt(map1.get("value").toString()) + 1);
                        }
                    }
                    if (!map.isEmpty()) {
                        mapList.add(map);
                    }
                    continue;
                }
            }
        }
        return mapList;
    }

    /**
     * <p>方法:毕业生流向图 getGraduationMapList TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/16 15:12  </li>
     * </ul>
     */
    public List<List<Map<String, Object>>> getGraduationMapList(Map<String, Object> dataMap) {
        List<Map<String, Object>> jyschemeList = commonDao.getGraduationMapList(dataMap);
        List<List<Map<String, Object>>> resultList = new ArrayList<>();
        Map<String, Object> fromMap = new HashMap<>(1);
        fromMap.put("name", "杭州");
        for (Map<String, Object> jyscheme : jyschemeList) {
            List<Map<String, Object>>  dataList = new ArrayList<>(2);
            dataList.add(fromMap);
            dataList.add(jyscheme);
            resultList.add(dataList);
        }
        return resultList;
    }

}