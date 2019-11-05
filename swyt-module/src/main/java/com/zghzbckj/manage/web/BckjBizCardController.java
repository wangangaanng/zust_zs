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
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.service.BckjBizCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * ccController
 *
 * @author cc
 * @version 2019-11-05
 */
@Controller
@RequestMapping(value = "bckjBizCard")
public class BckjBizCardController extends BaseController {
    @Autowired
    private BckjBizCardService bckjBizCardService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizCardService.findPageBckjBizCard(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(e + "获取bckjBizCard列表失败\r\n" + e.getStackTrace()[0], e);
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
            List data = bckjBizCardService.removeOrder(codes);
            return ResponseMessage.sendOK(data);
        } catch (Exception e) {
            log.error(e + "删除BckjBizCard列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return ResponseMessage.sendOK(bckjBizCardService.saveBckjBizCard(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjBizCard信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizCardService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizCard\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage upload(PublicDataVO dataVO, HttpServletRequest request) {
        try {
            String postData = "";
            Set<String> set = request.getParameterMap().keySet();
            for (String s : set) {
                postData += s + "=";
                postData += request.getParameterMap().get(s)[0];
            }
            if (TextUtils.isEmpty(postData)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL,"不存在请求参数");
            }
            if(postData.endsWith("="))
                postData = postData.replaceAll("=","").trim();
            bckjBizCardService.dealPostData(postData);
            return ResponseMessage.sendOK(Boolean.TRUE);
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取三位一体身份证识别上传失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

}