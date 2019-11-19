/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.beust.jcommander.internal.Maps;
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
import com.zghzbckj.manage.entity.BckjBizJtcyxx;
import com.zghzbckj.manage.service.BckjBizJtcyxxService;
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
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "bckjBizJtcyxx")
public class BckjBizJtcyxxController extends BaseController {
    @Autowired
    private BckjBizJtcyxxService bckjBizJtcyxxService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizJtcyxxService.findPageBckjBizJtcyxx(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(e + "获取bckjBizJtcyxx列表失败\r\n" + e.getStackTrace()[0], e);
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
            List data = bckjBizJtcyxxService.removeOrder(codes);
            return ResponseMessage.sendOK(data);
        } catch (Exception e) {
            log.error(e + "删除BckjBizJtcyxx列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return ResponseMessage.sendOK(bckjBizJtcyxxService.saveBckjBizJtcyxx(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjBizJtcyxx信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizJtcyxxService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizJtcyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>方法:finish TODO完善家庭成员信息 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/24 11:03  </li>
     * </ul>
     */
    @RequestMapping(value = "finish", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage finish(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "yhRefOwid", "dataList");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizJtcyxxService.finish(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("完善家庭成员信息失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /***
     * <p>方法:getInfo TODO获取家庭成员信息 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/24 11:50  </li>
     * </ul>
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "yhRefOwid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            List list=bckjBizJtcyxxService.findListByParams(mapData, "a.xssx");
            return ResponseMessage.sendOK(com.zghzbckj.util.TextUtils.base64Code(JsonUtil.toJson(list)));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("家庭成员信息获取失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @PostMapping(value = "getByBm")
    @ResponseBody
    public ResponseMessage getByBm(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            Map params = Maps.newHashMap();
            params.put("yhRefOwid", dataMap.get("userRefOwid"));
            List<BckjBizJtcyxx> cyxxList = bckjBizJtcyxxService.findListByParams(params, "a.xssx");
            return ResponseMessage.sendOK(cyxxList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}