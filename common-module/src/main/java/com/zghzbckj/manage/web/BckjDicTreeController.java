/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.google.common.collect.Lists;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.service.BckjDicTreeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 图文Controller
 *
 * @author cg
 * @version 2019-03-15
 */
@Controller
@RequestMapping(value = "bckjDicTree")
public class BckjDicTreeController {
    private static final Logger log = Logger.getLogger(BckjDicTreeController.class);

    @Autowired
    private BckjDicTreeService bckjDicTreeService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjDicTreeService.findPageBckjDicTree(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjDicTree列表失败\r\n" + e.getStackTrace()[0], e);
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
            List<Long> codes = Lists.newArrayList();
            for (Object obj : list) {
                String owid = ((Map<String, Object>) obj).get("owid").toString();
                codes.add(Long.valueOf(owid));
            }
            ResponseMessage data = bckjDicTreeService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjDicTree列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjDicTreeService.saveBckjDicTree(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjDicTree信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjDicTreeService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjDicTree\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "listDicTree/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDicTree(@PathVariable("type") Integer type, HttpServletRequest request,
                                       PublicDataVO dataVO) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "type is null ");
        }
        return bckjDicTreeService.listAllDicByType(type);
    }

    @RequestMapping(value = "listAddrDicTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAddrDicTree(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(bckjDicTreeService.listAddrTree(filters));
    }
}