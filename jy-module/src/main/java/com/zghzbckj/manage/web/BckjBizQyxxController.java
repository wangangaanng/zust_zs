/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.service.BckjBizQyxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizQyxx")
public class BckjBizQyxxController extends BaseController {
    @Autowired
    private BckjBizQyxxService bckjBizQyxxService;


    @RequestMapping(value = "/getList/{state}")
    @ResponseBody
    public ResponseMessage getListApi(@PathVariable("state") Integer state, PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizQyxxService.findPageBckjBizQyxx(filters, state,dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizQyxx列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizQyxxService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizQyxx列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizQyxxService.saveBckjBizQyxx(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizQyxx信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizQyxxService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 企业详情
     *
     * @param dataVO
     * @return
     */
    @RequestMapping(value = "getOneCompany", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOneCompany(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizQyxxService.getOneCompany(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 companyRegister.java : <p>
     * <p>说明：企业注册</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 10:06
     * </pre>
     */
    @RequestMapping(value = "companyRegister", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage companyRegister(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "qyYyzzzp", "qyTysh");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizQyxxService.companyRegister(mapData);
            if ("true".equals(resultMap.get("result").toString())) {
                return ResponseMessage.sendOK(resultMap.get("bean"));
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
            }
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }



    /**
     * <p>接口 login.java : <p>
     * <p>说明：企业登录</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 14:35
     * </pre>
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "qyFrsfz", "qyTysh");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizQyxxService.login(mapData);
            if ("true".equals(resultMap.get("result").toString())) {
                return ResponseMessage.sendOK("");
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
            }
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "fixCompany", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage fixCompany(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizQyxxService.fixCompany(mapData);
            if ("true".equals(resultMap.get("result").toString())) {
                return ResponseMessage.sendOK("");
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
            }
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}