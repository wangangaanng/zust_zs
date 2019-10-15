/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.service.BckjBizLqxsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "bckjBizLqxs")
public class BckjBizLqxsController extends BaseController {
    @Autowired
    private BckjBizLqxsService bckjBizLqxsService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizLqxsService.findPageBckjBizLqxs(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizLqxs列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizLqxsService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizLqxs列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizLqxsService.saveBckjBizLqxs(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizLqxs信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizLqxsService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizLqxs\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
    *<p>方法:lqcx TODO录取查询 </p>
    *<ul>
     *<li> @param dataVO TODO</li>
    *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/15 14:52  </li>
    *</ul>
    */
    @RequestMapping(value = "lqcx", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage lqcx(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            String sfzh= MapUtils.getString(mapData,"sfzh");
            String zkzh= MapUtils.getString(mapData,"ksh");
            if(TextUtils.isEmpty(sfzh)&& TextUtils.isEmpty(zkzh)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,"准考证和身份证号不能同时为空");
            }
            return ResponseMessage.sendOK(bckjBizLqxsService.getLqcx(mapData));
        } catch (Exception e) {
            log.info("录取查询失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }
}