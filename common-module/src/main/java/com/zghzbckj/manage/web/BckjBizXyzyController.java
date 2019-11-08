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
import com.zghzbckj.manage.entity.BckjBizXyzy;
import com.zghzbckj.manage.entity.BckjDicMenu;
import com.zghzbckj.manage.service.BckjBizXyzyService;
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


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizXyzy")
public class BckjBizXyzyController extends BaseController {
    @Autowired
    private BckjBizXyzyService bckjBizXyzyService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizXyzyService.findPageBckjBizXyzy(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizXyzy列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizXyzyService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizXyzy列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizXyzyService.saveBckjBizXyzy(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizXyzy信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizXyzyService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizXyzy\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    //类型树
    @RequestMapping(value = "listTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreePolicy( PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(bckjBizXyzyService.listTree(filters));
    }


    @RequestMapping(value = "removeTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeTree(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizXyzyService.removeTree(mapData));
        } catch (Exception e) {
            log.info("删除节点失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    //用于节点移动等操作
    @RequestMapping(value = "updateTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateTree( PublicDataVO data) {
        List<Map> datas = JsonUtil.jsonToList(data.getData(), Map.class);
        List<BckjBizXyzy> xyzys = new ArrayList<>(datas.size());
        for (Map menuData : datas) {
            BckjBizXyzy bckjBizXyzy  = JsonUtil.map2Bean(menuData, BckjBizXyzy.class);
            xyzys.add(bckjBizXyzy);
        }
        if (null == xyzys || xyzys.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "");
        }
        try {
            bckjBizXyzyService.saveOrUpdateAll(xyzys);
            return ResponseMessage.sendOK(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }

    }


    @RequestMapping(value = "saveTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveTree(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "mz");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizXyzyService.saveTree(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjDicMenu信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }



    @RequestMapping(value = "getZyList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getZyList(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "parentId");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizXyzyService.getZyList(mapData));
        } catch (Exception e) {

            log.error(e + "初始BckjBizXyzy\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}