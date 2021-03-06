package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
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

import java.util.Map;

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
    public ResponseMessage test(){
        return ResponseMessage.sendOK("OK");
    }

    /**
     *<p>功能描述:历年统计饼图 lntjPieChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/12 13:47</li>
     *</ul>
     */
    @PostMapping(value = "lntjPieChart")
    @ResponseBody
    public ResponseMessage getLntj(PublicDataVO dataVO) {
        try {
            Map<String, Object> result = commonService.getLntjPie();
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:招生计划饼图 zsjhPieChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/12 13:47</li>
     *</ul>
     */
    @PostMapping(value = "zsjhPieChart")
    @ResponseBody
    public ResponseMessage getZsjh(PublicDataVO dataVO) {
        try {
            Map<String, Object> result = commonService.getZsjhPie();
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:考生报名饼图 ksbmPieChart</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/12 14:51</li>
     *</ul>
     */
    @PostMapping(value = "ksbmPieChart")
    @ResponseBody
    public ResponseMessage getKsbm(PublicDataVO dataVO) {
        try {
            Map<String, Object> result = commonService.getKsbmPie();
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @PostMapping(value = "cjcxPieChart")
    @ResponseBody
    public ResponseMessage getCjcx(PublicDataVO dataVO) {
        try {
            Map<String, Object> result = commonService.getCjcxPie();
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping(value = "lqxsBarChart")
    @ResponseBody
    public ResponseMessage getLqxs(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        if (TextUtils.isEmpty(dataMap.get("limit"))) {
            dataMap.put("limit", 10);
        }
        try {
            Map<String, Object> result = commonService.getLqxsBar(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

}