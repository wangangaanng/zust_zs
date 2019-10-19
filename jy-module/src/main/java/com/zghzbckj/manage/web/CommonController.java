package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@RestController
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    CommonService commonService;

    @PostMapping(value = "test")
    public ResponseMessage test() {
        return ResponseMessage.sendOK("OK");
    }

    /**
     * <p>功能描述:查询企业数、职位数等 countNumber</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/12 11:56</li>
     * </ul>
     */
    @PostMapping(value = "countNumber")
    @ResponseBody
    public ResponseMessage countNumber(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        dataMap.put("state", "2");
        Map<String, Object> result = commonService.countNumber(dataMap);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:饼图统计（行业类别、公司规模） pieChart</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/12 13:02</li>
     * </ul>
     */
    @PostMapping(value = "pieChart")
    @ResponseBody
    public ResponseMessage pieChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        if ("1".equals(MapUtils.getString(dataMap, "type"))) {
            dataMap.put("dicType", "20001");
        } else if ("2".equals(MapUtils.getString(dataMap, "type"))) {
            dataMap.put("dicType", "20002");
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
        }
        Map<String, Object> result = commonService.generatePieCharts(dataMap);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:企业地区柱状图 companyArea</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/12 15:31</li>
     * </ul>
     */
    @PostMapping(value = "companyArea")
    @ResponseBody
    public ResponseMessage companyArea(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        List<String> recentYears = new ArrayList<>(3);
        if (TextUtils.isEmpty(dataMap.get("year"))) {
            Calendar calendar = Calendar.getInstance();
            int thisYear = calendar.get(Calendar.YEAR);
            for (int i = 0; i < 3; i++) {
                recentYears.add(String.valueOf(thisYear));
                thisYear = thisYear - 1;
            }
            dataMap.put("recentYears", recentYears);
        } else {
            recentYears.add(MapUtils.getString(dataMap, "year"));
            dataMap.put("recentYears", recentYears);
        }
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (String year : recentYears) {
            mapList.add(commonService.getCompanyArea(year));
        }
        result.put("yAxis", mapList.get(0).get("yAxisData"));
        result.put("yData", mapList);
        result.put("legendData", recentYears);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:根据年度12个月企业注册统计 companyReg</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/12 17:42</li>
     * </ul>
     */
    @PostMapping(value = "companyReg")
    @ResponseBody
    public ResponseMessage companyReg(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        List<String> monthList = listMonth();
        Map<String, Object> result = commonService.getCompanyReg(dataMap);
        result.put("xData", monthList);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:职来职往、企业招聘公告等折线图 lineChart</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/13 16:44</li>
     * </ul>
     */
    @PostMapping(value = "lineChart")
    @ResponseBody
    public ResponseMessage lineChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        List<String> legendData = Arrays.asList("职位", "职来职往", "社会招聘会", "企业", "宣讲会");
        List<String> monthList = listMonth();
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        //折线名称
        result.put("legendData", legendData);
        //x轴12个月份
        result.put("xData", monthList);
        //y轴职位数量
        dataMap.put("zwlx", "0");
        dataMap.put("zwlxmc", "职位");
        dataList.add(commonService.getLineChart(dataMap));
        //y轴职来职往数量
        dataMap.put("zwlx", "1");
        dataMap.put("zwlxmc", "职来职往");
        dataList.add(commonService.getLineChart(dataMap));
        //y轴社会招聘会数量
        dataMap.put("zwlx", "2");
        dataMap.put("zwlxmc", "社会招聘会");
        dataList.add(commonService.getLineChart(dataMap));
        //y轴企业数量
        dataMap.put("zwlx", "3");
        dataMap.put("zwlxmc", "企业");
        dataList.add(commonService.getLineChart(dataMap));
        //y轴宣讲会数量
        dataMap.put("zwlx", "4");
        dataMap.put("zwlxmc", "宣讲会");
        dataList.add(commonService.getLineChart(dataMap));
        result.put("yData", dataList);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:受关注企业、职位柱状图 barChart</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/14 11:30</li>
     * </ul>
     */
    @PostMapping(value = "barChart")
    @ResponseBody
    public ResponseMessage barChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        if (TextUtils.isEmpty(dataMap.get("limit"))) {
            //默认取前5
            dataMap.put("limit", 5);
        } else {
            dataMap.put("limit", MapUtils.getInt(dataMap, "limit"));
        }
        Map<String, Object> result = commonService.getBarChart(dataMap);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:学生来源热力图 studentFrom</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/15 14:58</li>
     * </ul>
     */
    @PostMapping(value = "studentFrom")
    @ResponseBody
    public ResponseMessage studentFrom(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        try {
            List<Map<String, Object>> mapList = commonService.getMapList(dataMap);
            return ResponseMessage.sendOK(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:毕业生流向 graduationArea</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/16 14:09</li>
     *</ul>
     */
    @PostMapping(value = "graduationArea")
    @ResponseBody
    public ResponseMessage graduationArea(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        try {
            List<List<Map<String, Object>>> mapList = commonService.getGraduationMapList(dataMap);
            return ResponseMessage.sendOK(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>方法:12个月份 listMonth TODO </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/14 11:29  </li>
     * </ul>
     */
    private List<String> listMonth() {
        List<String> result = new ArrayList<>(8);
        for (int i = 1; i <= 12; i++) {
            result.add(i + "月");
        }
        return result;
    }

}