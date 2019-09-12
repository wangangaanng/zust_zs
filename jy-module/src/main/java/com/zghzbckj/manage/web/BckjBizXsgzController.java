/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.zghzbckj.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.CommonConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zghzbckj.manage.service.BckjBizXsgzService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * ccController
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizXsgz")
public class BckjBizXsgzController extends BaseController {

	@Autowired
	private BckjBizXsgzService bckjBizXsgzService;


	@RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizXsgzService.findPageBckjBizXsgz(filters, dataVO.getPageNo(), dataVO.getPageSize());
    } catch (Exception e) {
            log.error(e+"获取bckjBizXsgz列表失败\r\n"+e.getStackTrace()[0] ,e);
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
            ResponseMessage data = bckjBizXsgzService.removeOrder(codes);
            return data;
            } catch (Exception e) {
            log.error(e+"删除BckjBizXsgz列表失败\r\n" +e.getStackTrace()[0] , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

            @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage saveInfo(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizXsgzService.saveBckjBizXsgz(mapData);
            } catch (Exception e) {
            log.error(e+"保存BckjBizXsgz信息失败\r\n"+e.getStackTrace()[0]  , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

            @RequestMapping(value = "getOne", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage getOne(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData,"owid");
            if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizXsgzService.get(mapData.get("owid").toString()));
            } catch (Exception e) {

            log.error(e+"初始BckjBizXsgz\r\n" +e.getStackTrace()[0] ,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }



    /**
     * <p>功能描述:学生签到 或关注企业职位</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11</li>
     * </ul>
     */
    @RequestMapping(value = "signInOrScribe", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage signInOrScribe(PublicDataVO dataVO) {
        try {
            Map<String, Object> datamap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(datamap,"yhRefOwid","jobRefOwid","xxlb");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizXsgzService.signInOrScribe(datamap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

}