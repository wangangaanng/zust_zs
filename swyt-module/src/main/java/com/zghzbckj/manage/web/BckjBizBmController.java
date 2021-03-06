/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.common.SwytConstant;
import com.zghzbckj.manage.entity.BckjBizBm;
import com.zghzbckj.manage.service.BckjBizBmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ccController
 *
 * @author cc
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "bckjBizBm")
public class BckjBizBmController extends BaseController {
    @Autowired
    private BckjBizBmService bckjBizBmService;


    @RequestMapping(value = "/getList/{state}")
    @ResponseBody
    public ResponseMessage getListApi(@PathVariable("state") Integer state, PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizBmService.findPageBckjBizBm(filters, state, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(e + "获取bckjBizBm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "/getHistoryList")
    @ResponseBody
    public ResponseMessage getHistoryList( PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizBmService.getHistoryList(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(e + "获取bckjBizBm列表失败\r\n" + e.getStackTrace()[0], e);
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
            List data = bckjBizBmService.removeOrder(codes);
            return ResponseMessage.sendOK(data);
        } catch (Exception e) {
            log.error(e + "删除BckjBizBm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return ResponseMessage.sendOK(bckjBizBmService.saveBckjBizBm(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjBizBm信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizBmService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizBm\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>方法:submit TODO报考专业提交 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 10:08  </li>
     * </ul>
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage submit(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "xklb", "bklb", "zyOwid", "userRefOwid", "xxbh");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.submit(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("报考专业提交 失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    /**
     * 确认申请表
     *
     * @param dataVO
     * @return
     */
    @RequestMapping(value = "confirmApply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage confirmApply(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.confirmApply(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info(" 确认申请表失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    /***
     * <p>方法:promise TODO 承诺书提交</p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 15:38  </li>
     * </ul>
     */
    @RequestMapping(value = "promise", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage promise(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.promise(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("承诺书提交失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /***
     * <p>方法:getResult TODO获取报名信息 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 16:13  </li>
     * </ul>
     */
    @RequestMapping(value = "getResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getResult(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
           BckjBizBm bm=bckjBizBmService.getResult(mapData);
            if(null==bm){
                return ResponseMessage.sendOK(bm);
            }
            return ResponseMessage.sendOK(com.zghzbckj.util.TextUtils.base64Code(JackSonJsonUtils.toJson(bm)));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取报名失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    /**
     * <p>方法:submitJft TODO提交缴费截图 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 16:15  </li>
     * </ul>
     */
    @RequestMapping(value = "/submitJft", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage submitJft(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid", "jfpzZp", "jfsj");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.submitJft(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("提交缴费截图失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:getApply TODO生成申请表预览 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/26 15:51  </li>
     * </ul>
     */
    @RequestMapping(value = "getApply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getApply(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.getApply(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("生成申请表预览 失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:sendEmail TODO发送申请书到邮箱 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 12:21  </li>
     * </ul>
     */
    @RequestMapping(value = "sendView", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendView(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "yx", "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.sendView(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("发送面试通知单到邮箱失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    /**
     * <p>方法:sendEmail TODO发送申请书到邮箱 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 12:21  </li>
     * </ul>
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendEmail(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "yx", "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.sendApplyEmail(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("发送申请书到邮箱失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /***
     * <p>方法:notice TODO 面试通知单预览</p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 21:02  </li>
     * </ul>
     */
    @RequestMapping(value = "notice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage notice(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "applyOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBmService.getNotice(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("面试通知单预览失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "bmPass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage bmPass(PublicDataVO dataVo) throws Exception {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVo.getData());
        List<String> codes = new ArrayList<String>();
        codes.add(mapData.get("owid").toString());
        //拒绝 状态为4  通过为5
        Integer state = 5;
        Map resultMap = Maps.newHashMap();
        resultMap = bckjBizBmService.submitPurchaseBack(codes, state, mapData);
        if ("true".equals(resultMap.get("result").toString())) {
            //数据回写
            return ResponseMessage.sendOK(resultMap.get("bean"));
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
        }
    }


    @RequestMapping(value = "moneyPass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage moneyPass(PublicDataVO dataVo) throws Exception {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVo.getData());
        List<String> codes = new ArrayList<String>();
        codes.add(mapData.get("owid").toString());
        //拒绝 状态为4  通过为5
        Integer state = 7;
        Map resultMap = Maps.newHashMap();
        resultMap = bckjBizBmService.submitPurchaseBack(codes, state, mapData);
        if ("true".equals(resultMap.get("result").toString())) {
            //数据回写
            return ResponseMessage.sendOK(resultMap.get("bean"));
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
        }
    }


    @RequestMapping(value = "bmReject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage bmReject(PublicDataVO publicDataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(publicDataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid", "state");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        BckjBizBm bm = bckjBizBmService.get(mapData.get("owid").toString());
        if (bm == null) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "查无");
        }
        String lxdh = bm.getLxdh();
        String content = "";
        Integer state = Integer.parseInt(mapData.get("state").toString());
        //state 4 拒绝   -1打回修改
        if (4 == state) {
            if (!TextUtils.isEmpty(mapData.get("jjly"))) {
                bm.setJjly(mapData.get("jjly").toString());
            }
            bm.setXybnr(SwytConstant.BMJJ);
            content = SwytConstant.REJECT_MESS;
            if (!TextUtils.isEmpty(bm.getJjly())) {
                content += "原因：" + bm.getJjly();
            }
            try {
                MessageUtil.sendMessage(lxdh, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (5 == state) {
            if (!TextUtils.isEmpty(mapData.get("jjly"))) {
                bm.setJjly(mapData.get("jjly").toString());
            }
            bm.setXybnr(SwytConstant.BMDJF);
        }
        if (-1 == state) {
            if (!TextUtils.isEmpty(mapData.get("memo"))) {
                bm.setMemo(mapData.get("memo").toString());
            }
            bm.setXybnr(SwytConstant.CXTJBMSQ);
            content = SwytConstant.FIX_MESS;
            if (!TextUtils.isEmpty(bm.getMemo())) {
                content += "原因：" + bm.getMemo();
            }
            try {
                MessageUtil.sendMessage(lxdh, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bm.setState(state);
        bckjBizBmService.saveOrUpdate(bm);
        return ResponseMessage.sendOK(bm);
    }


    /**
     * <p>接口 listHkName.java : <p>
     * <p>说明：动态字段 会考</p>
     * <pre>
     * @author cc
     * @date 2019/11/4 10:27
     * </pre>
     */
    @PostMapping(value = "listHkName")
    @ResponseBody
    public ResponseMessage listHkName(PublicDataVO dataVO) {
        List<Map> questionList = bckjBizBmService.listDicByType(SwytConstant.HKCJ);
        if (TextUtils.isEmpty(questionList) || questionList.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "会考列表为空");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> question : questionList) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("kjName", question.get("DIC_VAL2"));
            objectMap.put("kjAttribute", question.get("DIC_VAL2"));
            objectMap.put("kjLabelid", question.get("DIC_VAL2"));
            objectMap.put("kjAttributeDisplay", question.get("DIC_VAL2"));
            objectMap.put("kjBindKey", question.get("DIC_VAL2"));
            result.add(objectMap);
        }
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>接口 listZhName.java : <p>
     * <p>说明：动态字段 综合成绩</p>
     * <pre>
     * @author cc
     * @date 2019/11/4 15:08
     * </pre>
     */
    @PostMapping(value = "listZhName")
    @ResponseBody
    public ResponseMessage listZhName(PublicDataVO dataVO) {
        List<Map> questionList = bckjBizBmService.listDicByType(SwytConstant.ZHCP);
        if (TextUtils.isEmpty(questionList) || questionList.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "综合测评列表为空");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> question : questionList) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("kjName", question.get("DIC_VAL2"));
            objectMap.put("kjAttribute", question.get("DIC_VAL2"));
            objectMap.put("kjLabelid", question.get("DIC_VAL2"));
            objectMap.put("kjAttributeDisplay", question.get("DIC_VAL2"));
            objectMap.put("kjBindKey", question.get("DIC_VAL2"));
            result.add(objectMap);
        }
        return ResponseMessage.sendOK(result);
    }


    @PostMapping(value = "genZkz")
    @ResponseBody
    public ResponseMessage genZkz(PublicDataVO dataVO) {
        try {
            bckjBizBmService.genZkz();
            return ResponseMessage.sendOK("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 recordCjInfo.java : <p>
     * <p>说明：三位一体成绩导入</p>
     * <pre>
     * @author cc
     * @date 2019/11/6 13:51
     * </pre>
     */
    @RequestMapping(value = "recordCjInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordCjInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizBmService.recordCjInfo(dataMap.get("path").toString());
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    //首页统计
    @PostMapping(value = "todoMan")
    @ResponseBody
    public ResponseMessage todoMan(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "state");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
        }
        try {
            Integer todoMan = bckjBizBmService.todoMan(dataMap);
            return ResponseMessage.sendOK(todoMan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>接口 bmPie.java : <p>
     * <p>说明：饼图</p>
     * <pre>
     * @author cc
     * @date 2019/11/8 11:47
     * </pre>
     */
    @PostMapping(value = "bmPie")
    @ResponseBody
    public ResponseMessage bmPie(PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "type");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_NOPARAMS);
        }
        try {
            Map<String, Object> result = bckjBizBmService.bmPie(dataMap);
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}