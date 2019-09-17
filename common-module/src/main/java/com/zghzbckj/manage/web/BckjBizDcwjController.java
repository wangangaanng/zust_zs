/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.entity.BckjBizDcwj;
import com.zghzbckj.manage.service.BckjBizDcwjService;
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
@RequestMapping(value = "bckjBizDcwj")
public class BckjBizDcwjController extends BaseController {
    @Autowired
    private BckjBizDcwjService bckjBizDcwjService;

    /**
     *<p>功能描述:调查问卷列表 dcwjList</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/11 16:03</li>
     *</ul>
     */
    @PostMapping(value = "dcwjList")
    @ResponseBody
    public ResponseMessage dcwjList(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "wzbh");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "网站编号为空");
            }
            PageInfo<Map<String, Object>> pageInfo = bckjBizDcwjService.listAllQuestionnaire(dataMap, dataVO.getPageNo(), dataVO.getPageSize());
            return ResponseMessage.sendOK(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    /**
     *<p>功能描述:调查问卷详情 dcwjDetail</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/12 10:27</li>
     *</ul>
     */
    @PostMapping(value = "dcwjDetail")
    @ResponseBody
    public ResponseMessage dcwjDetail(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"wzbh", "dcwjRefOwid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "参数为空");
            }
            BckjBizDcwj questionnaire = bckjBizDcwjService.get(dataMap.get("dcwjRefOwid").toString());
            if (TextUtils.isEmpty(questionnaire)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "调查问卷为空");
            }
            List<Map<String, Object>> questionList = bckjBizDcwjService.listAllQuestions(dataMap);
            Map<String, Object> result = new HashMap<>();
            result.put("wjmc", questionnaire.getWjmc());
            result.put("wjjj", questionnaire.getWjjj());
            result.put("wjjjtp", questionnaire.getWjjjtp());
            result.put("wjsm", questionnaire.getWjsm());
            result.put("kssj", questionnaire.getKssj());
            result.put("jssj", questionnaire.getJssj());
            result.put("questionList", questionList);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    /**
     *<p>功能描述:问卷答案提交 submit</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/12 14:41</li>
     *</ul>
     */
    @PostMapping(value = "submit")
    @ResponseBody
    public ResponseMessage submit(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "dcwjRefOwid", "answerList");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "参数为空");
            }
            BckjBizDcwj questionnaire = bckjBizDcwjService.get(dataMap.get("dcwjRefOwid").toString());
            if (TextUtils.isEmpty(questionnaire)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "调查问卷为空");
            }
            return bckjBizDcwjService.saveAnswer(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizDcwjService.findPageBckjBizDcwj(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizDcwj列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizDcwjService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizDcwj列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizDcwjService.saveBckjBizDcwj(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizDcwj信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizDcwjService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizDcwj\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}