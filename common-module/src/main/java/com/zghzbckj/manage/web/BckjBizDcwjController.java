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
import com.zghzbckj.manage.entity.BckjBizDcwj;
import com.zghzbckj.manage.entity.BckjBizDcwjJg;
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.service.BckjBizDcwjJgService;
import com.zghzbckj.manage.service.BckjBizDcwjService;
import com.zghzbckj.manage.service.BckjBizDcwjTmService;
import com.zghzbckj.manage.service.BckjBizYhxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


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
    @Autowired
    BckjBizDcwjJgService bckjBizDcwjJgService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizDcwjTmService bckjBizDcwjTmService;


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
            //设置分页属性
            Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
            Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
            //只显示已发布的调查问卷
            dataMap.put("state", "0");
            PageInfo<Map<String, Object>> pageInfo = bckjBizDcwjService.listAllQuestionnaire(dataMap, pageNo, pageSize);
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
            //判断是否存在调查问卷
            if (TextUtils.isEmpty(questionnaire)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "调查问卷为空");
            }
            List<Map<String, Object>> questionList = bckjBizDcwjService.listQuestions(dataMap);
            Map<String, Object> result = new HashMap<>();
            result.put("wjmc", questionnaire.getWjmc());
            result.put("wjjj", questionnaire.getWjjj());
            result.put("wjjjtp", questionnaire.getWjjjtp());
            result.put("wjsm", questionnaire.getWjsm());
            result.put("kssj", questionnaire.getKssj());
            result.put("jssj", questionnaire.getJssj());
            result.put("questionList", questionList);
            //判断调查人数是否已满。调查人数默认为0，代表不限制
            int maxNumber = bckjBizDcwjJgService.countPeople(dataMap.get("dcwjRefOwid").toString());
            if (maxNumber >= questionnaire.getDcrs() && !questionnaire.getDcrs().toString().equals("0")) {
                result.put("tips", "调查人数已满，无需再填写");
                return ResponseMessage.sendOK(result);
            }
            //判断是否已答题
            if (!TextUtils.isEmpty(dataMap.get("yhOwid")) && questionnaire.getSfdl() == 1) {
                dataMap.remove("orderBy");
                BckjBizDcwjJg jg = bckjBizDcwjJgService.findOneByParams(dataMap, null);
                if (!TextUtils.isEmpty(jg)) {
                    result.put("tips", "您已填写过该问卷");
                    return ResponseMessage.sendOK(result);
                }
            }
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
            //查找调查问卷
            BckjBizDcwj questionnaire = bckjBizDcwjService.get(dataMap.get("dcwjRefOwid").toString());
            if (TextUtils.isEmpty(questionnaire)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "调查问卷为空");
            }
            //判断调查问卷是否已开始
            if (System.currentTimeMillis() < questionnaire.getKssj().getTime() ||
                    System.currentTimeMillis() > questionnaire.getJssj().getTime()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "不在调查时间范围内");
            }
            //判断调查人数是否已满
            int maxNumber = bckjBizDcwjJgService.countPeople(dataMap.get("dcwjRefOwid").toString());
            if (maxNumber >= questionnaire.getDcrs() && !questionnaire.getDcrs().toString().equals("0")) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "调查人数已满，无需再填写");
            }
            //若有答题人id，查找答题人姓名，若无答题人id，表示为
            if (!TextUtils.isEmpty(dataMap.get("dtrId"))) {
                BckjBizYhxx yhxx = bckjBizYhxxService.get(dataMap.get("dtrId").toString());
                if (TextUtils.isEmpty(yhxx)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "用户信息为空");
                }
                BckjBizDcwjJg jg = bckjBizDcwjJgService.findOneByParams(dataMap, null);
                if (!TextUtils.isEmpty(jg)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "您已填写过该问卷");
                }
                dataMap.put("dtrXm", yhxx.getXm());
            }
            //存入问卷名称
            dataMap.put("wjmc", questionnaire.getWjmc());
            return bckjBizDcwjService.saveAnswer(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    /**
     *<p>功能描述:后台根据问卷的owid列出所有题目 listQuestions</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/19 16:12</li>
     *</ul>
     */
    @PostMapping(value = "listQuestions")
    @ResponseBody
    public ResponseMessage listQuestions(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            dataMap.put("dcwjRefOwid", dataMap.get("owid"));
            List<Map<String, Object>> questionList = bckjBizDcwjService.listAllQuestions(dataMap);
            return ResponseMessage.sendOK(questionList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:后台保存调查问卷 saveQuestionnaire</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/19 17:32</li>
     *</ul>
     */
    @PostMapping(value = "saveQuestionnaire")
    @ResponseBody
    public ResponseMessage saveQuestionnaire(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "wzbh", "wjmc");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "请填写网站编号和问卷名称");
        }
        //判断开始时间和结束时间
        long Kssj = DateUtil.getDate(dataMap.get("kssj").toString()).getTime();
        long jssj = DateUtil.getDate(dataMap.get("jssj").toString()).getTime();
        if (Kssj > jssj) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "开始时间不能大于结束时间");
        }
        BckjBizDcwj bckjBizDcwj = JsonUtil.map2Bean(dataMap, BckjBizDcwj.class);
        List<BckjBizDcwjTm> tmList = new ArrayList<>();
        if (!TextUtils.isEmpty(dataMap.get("dataList1"))) {
            List<Map<String, Object>> dataList1 = (List)dataMap.get("dataList1");
            for (Map<String, Object> data1 : dataList1) {
                BckjBizDcwjTm bckjBizDcwjTm = JsonUtil.map2Bean(data1, BckjBizDcwjTm.class);
                //判断是否为删除记录
                if (!TextUtils.isEmpty(data1.get("updateFlag")) && "2".equals(data1.get("updateFlag").toString())) {
                    bckjBizDcwjTmService.delete(bckjBizDcwjTm);
                    continue;
                }
                tmList.add(bckjBizDcwjTm);
            }
        }
        bckjBizDcwjService.saveAll(bckjBizDcwj, tmList);
        return ResponseMessage.sendOK("");
    }

    /**
     *<p>功能描述:发布调查问卷 setState</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/23 19:15</li>
     *</ul>
     */
    @PostMapping(value = "setState")
    @ResponseBody
    public ResponseMessage setState(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid", "state");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        BckjBizDcwj dcwj = bckjBizDcwjService.get(MapUtils.getString(dataMap, "owid"));
        if (TextUtils.isEmpty(dcwj)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "无此调查问卷");
        }
        dcwj.setState(MapUtils.getInt(dataMap, "state"));
        bckjBizDcwjService.saveOrUpdate(dcwj);
        return ResponseMessage.sendOK(dcwj);
    }

    /**
     *<p>功能描述:复制调查问卷 copyDcwj</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/27 10:37</li>
     *</ul>
     */
    @PostMapping(value = "copyDcwj")
    @ResponseBody
    public ResponseMessage copyDcwj(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        BckjBizDcwj dcwj = bckjBizDcwjService.get(MapUtils.getString(dataMap, "owid"));
        List<BckjBizDcwjTm> tmList = bckjBizDcwjTmService.listDcwjTm(MapUtils.getString(dataMap, "owid"));
        if (TextUtils.isEmpty(dcwj)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "无此调查问卷");
        }
        //复制新的调查问卷，默认未发布 state=1
        dcwj.setOwid("");
        dcwj.setWjmc(dcwj.getWjmc() + "-复制");
        dcwj.setState(1);
        bckjBizDcwjService.save(dcwj);
        for (BckjBizDcwjTm tm : tmList) {
            tm.setOwid("");
            tm.setDcwjRefOwid(dcwj.getOwid());
            bckjBizDcwjTmService.save(tm);
        }
        return ResponseMessage.sendOK("OK");
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