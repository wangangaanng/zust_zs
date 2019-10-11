/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;
import com.zghzbckj.manage.service.BckjBizDcwjDtmxService;
import com.zghzbckj.manage.service.BckjBizDcwjJgService;
import com.zghzbckj.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizDcwjDtmx")
public class BckjBizDcwjDtmxController extends BaseController {
    @Autowired
    private BckjBizDcwjDtmxService bckjBizDcwjDtmxService;
    @Autowired
    BckjBizDcwjJgService bckjBizDcwjJgService;
    @Autowired
    BckjBizDcwjTmDao bckjBizDcwjTmDao;

    private Map<String, Object> map = new HashMap<>();

    /**
     *<p>功能描述:setOwidDcwj 添加调查问卷外键</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/10 17:12</li>
     *</ul>
     */
    @PostMapping(value = "setOwidDcwj")
    @ResponseBody
    public void setOwidDcwj(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            map.put("dcwjRefOwid", dataMap.get("owid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *<p>功能描述:问卷结果getResultList</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/10 14:36</li>
     *</ul>
     */
    @PostMapping(value = "getResultList")
    @ResponseBody
    public ResponseMessage getResultList(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            dataMap.put("dcwjRefOwid", dataMap.get("owid"));
            PageInfo<Map<String, Object>> pageInfo = bckjBizDcwjJgService.listResult(map, dataVO.getPageNo(), dataVO.getPageSize());
            return ResponseMessage.sendOK(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:动态字段 listQuestionName</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/10 11:47</li>
     *</ul>
     */
    @PostMapping(value = "listQuestionName")
    @ResponseBody
    public ResponseMessage listQuestionName(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
//        dataMap.put("dcwjRefOwid", dataMap.get("owid"));
        dataMap.put("dcwjRefOwid", "a2d7439e145b4ca8ad0c7519c37c46a4");
        List<Map<String, Object>> questionList = bckjBizDcwjTmDao.listQuestion(MapUtils.getString(map, "dcwjRefOwid"));
        if (TextUtils.isEmpty(questionList) || questionList.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "题目列表为空");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> question : questionList) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("kjName", question.get("tmmc"));
            objectMap.put("kjAttribute", question.get("tmmc"));
            objectMap.put("kjLabelid", question.get("tmmc"));
            objectMap.put("kjAttributeDisplay", question.get("dcwjtmRefOwid"));
            objectMap.put("kjBindKey", question.get("dcwjtmRefOwid"));
            result.add(objectMap);
        }
        return ResponseMessage.sendOK(result);
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizDcwjDtmxService.findPageBckjBizDcwjDtmx(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizDcwjDtmx列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping(value = "deleteList")
    @ResponseBody
    public ResponseMessage deleteList(PublicDataVO dataVO) {
        try {
            if (TextUtils.isEmpty(dataVO.getData())) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
            }

            List<Object> list = JsonUtil.jsonToList(dataVO.getData());
            List<String> codes = new ArrayList<String>(list.size());
            for (Object obj : list) {
                codes.add(((Map<String, Object>) obj).get("owid").toString());
            }
            ResponseMessage data = bckjBizDcwjDtmxService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizDcwjDtmx列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizDcwjDtmxService.saveBckjBizDcwjDtmx(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizDcwjDtmx信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "getOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOne(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizDcwjDtmxService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizDcwjDtmx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}