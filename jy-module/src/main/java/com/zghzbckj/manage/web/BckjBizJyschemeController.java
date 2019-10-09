/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.service.BckjBizJyschemeService;
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

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 就业方案Controller
 * @author wangangaanng
 * @version 2019-09-30
 */
@Controller
@RequestMapping(value = "bckjBizJyscheme")
public class BckjBizJyschemeController extends BaseController {
	@Autowired
	private BckjBizJyschemeService bckjBizJyschemeService;


	@RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
    return bckjBizJyschemeService.findPageBckjBizJyscheme(filters, dataVO.getPageNo(), dataVO.getPageSize());
    } catch (Exception e) {
    log.error(e+"获取bckjBizJyscheme列表失败\r\n"+e.getStackTrace()[0] , e);
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
            ResponseMessage data = bckjBizJyschemeService.removeOrder(codes);
            return data;
            } catch (Exception e) {
            log.error(e+"删除BckjBizJyscheme列表失败\r\n" +e.getStackTrace()[0] , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

            @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage saveInfo(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizJyschemeService.saveBckjBizJyscheme(mapData);
            } catch (Exception e) {
            log.error(e+"保存BckjBizJyscheme信息失败\r\n"+e.getStackTrace()[0]  , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

    /**
     * <p>功能描述:保存学生填写的就业方案</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/30</li>
     * </ul>
     */
    @PostMapping("saveJyFaInfo")
    @ResponseBody
    public ResponseMessage saveJyFaInfo(PublicDataVO dataVO){
	    try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizJyschemeService.saveJyFaInfo(dataMap);
        }
	    catch (Exception e){
	        log.error(CommonConstant.ERROR_MESSAGE,e);
	        return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:获取学生填写的就业方案的信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/30</li>
     * </ul>
     */
    @PostMapping("getJyBaseInfo")
    @ResponseBody
    public ResponseMessage getJyBaseInfo(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(2,"登入过期");
            }
            return bckjBizJyschemeService.getJyBaseInfo(dataMap);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 后台详情页面getone 根据 owid
     */
    @RequestMapping(value = "getOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOne(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizSyb\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}