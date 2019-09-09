/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import com.zghzbckj.manage.entity.BckjBizZsjh;
import com.zghzbckj.manage.service.BckjBizZsjhService;
import org.apache.log4j.Logger;




/**
 * ccController
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizZsjh")
public class BckjBizZsjhController extends BaseController {
	@Autowired
	private BckjBizZsjhService bckjBizZsjhService;


	@RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
    return bckjBizZsjhService.findPageBckjBizZsjh(filters, dataVO.getPageNo(), dataVO.getPageSize());
    } catch (Exception e) {
    log.error(e+"获取bckjBizZsjh列表失败\r\n"+e.getStackTrace()[0] , e);
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
            ResponseMessage data = bckjBizZsjhService.removeOrder(codes);
            return data;
            } catch (Exception e) {
            log.error(e+"删除BckjBizZsjh列表失败\r\n" +e.getStackTrace()[0] , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

            @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage saveInfo(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizZsjhService.saveBckjBizZsjh(mapData);
            } catch (Exception e) {
            log.error(e+"保存BckjBizZsjh信息失败\r\n"+e.getStackTrace()[0]  , e);
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
            return ResponseMessage.sendOK(bckjBizZsjhService.get(mapData.get("owid").toString()));
            } catch (Exception e) {

            log.error(e+"初始BckjBizZsjh\r\n" +e.getStackTrace()[0] ,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }



}