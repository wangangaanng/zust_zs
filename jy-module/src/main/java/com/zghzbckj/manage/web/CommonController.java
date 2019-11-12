package com.zghzbckj.manage.web;

import com.ourway.base.utils.*;
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
     * <p>功能描述:待举办招聘会、待举办宣讲会 todoJob</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/22 9:34</li>
     * </ul>
     */
    @PostMapping(value = "todoJob")
    @ResponseBody
    public ResponseMessage todoJob(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "zwlx");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
        }
        try {
            List<Map<String, Object>> result = commonService.listJob(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:待审核企业 todoCompany</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/10/22 10:12</li>
     * </ul>
     */
    @PostMapping(value = "todoCompany")
    @ResponseBody
    public ResponseMessage todoCompany(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        try {
            List<Map<String, Object>> result = commonService.listCompany(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:审核通过以及未审核通过企业的饼状图 companyBarChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/22 13:49</li>
     *</ul>
     */
    @PostMapping(value = "companyPieChart")
    @ResponseBody
    public ResponseMessage companyPieChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        try {
            Map<String, Object> result = commonService.getCompanyPie(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:职位按照行业的汇总饼状图 jobBarChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/22 13:49</li>
     *</ul>
     */
    @PostMapping(value = "jobPieChart")
    @ResponseBody
    public ResponseMessage jobPieChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        //0职位
        dataMap.put("zwlx", 0);
        //工作职能
        dataMap.put("groupBy", "zw_gzzn");
        try {
            Map<String, Object> result = commonService.getJobPie(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:招聘会、宣讲会饼状图 meetingBarChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/22 15:00</li>
     *</ul>
     */
    @PostMapping(value = "meetingPieChart")
    @ResponseBody
    public ResponseMessage meetingPieChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        //zwlx=3招聘会 zwlx=4宣讲会
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "zwlx");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
        }
        try {
            Map<String, Object> result = commonService.getMeetingPie(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:统计每月的职位数、招聘会数、宣讲会数柱状图 jobBarChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/22 15:16</li>
     *</ul>
     */
    @PostMapping(value = "jobBarChart")
    @ResponseBody
    public ResponseMessage jobBarChart(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        try {
            Map<String, Object> result = new HashMap<>();
            int minDate = MapUtils.getInt(dataMap, "minDate");
            int maxDate = MapUtils.getInt(dataMap, "maxDate");
            if (minDate > maxDate) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "开始日期不能大于结束日期");
            }
            result.put("series", commonService.getJobBar(dataMap));
            result.put("monthList", commonService.getMonthBetweenDates(MapUtils.getString(dataMap, "minDate"),
                    MapUtils.getString(dataMap, "maxDate")));
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

}