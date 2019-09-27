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
import com.zghzbckj.manage.entity.BckjDicMenu;
import com.zghzbckj.manage.service.BckjDicMenuService;
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
@RequestMapping(value = "bckjDicMenu")
public class BckjDicMenuController extends BaseController {
    @Autowired
    private BckjDicMenuService bckjDicMenuService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjDicMenuService.findPageBckjDicMenu(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjDicMenu列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    //类型树
    @RequestMapping(value = "listTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreePolicy(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(bckjDicMenuService.listTree(filters));
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
            ResponseMessage data = bckjDicMenuService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjDicMenu列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjDicMenuService.saveBckjDicMenu(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjDicMenu信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "saveTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveTree(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "name");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjDicMenuService.saveTree(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjDicMenu信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjDicMenuService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjDicMenu\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "removeMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeMenu(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjDicMenuService.removeMenu(mapData));
        } catch (Exception e) {
            log.info("删除节点失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    //用于节点移动等操作
    @RequestMapping(value = "updateMove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateMove(HttpServletRequest request, PublicDataVO data) {
        List<Map> datas = JsonUtil.jsonToList(data.getData(), Map.class);

        List<BckjDicMenu> movePolicy = new ArrayList<>(datas.size());
        for (Map menuData : datas) {
            BckjDicMenu menu = JsonUtil.map2Bean(menuData, BckjDicMenu.class);
            movePolicy.add(menu);
        }
        if (null == movePolicy || movePolicy.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "");
        }
        try {
            bckjDicMenuService.saveOrUpdateAll(movePolicy);
            return ResponseMessage.sendOK(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }

    }


    @RequestMapping(value = "getLmMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getLmMenu(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "wzbh", "fid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjDicMenuService.getLmMenu(mapData));
        } catch (Exception e) {
            log.info("获取一级栏目失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "getSyMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSyMenu(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "wzbh","bxlx");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjDicMenuService.getSyMenu(mapData));
        } catch (Exception e) {
            log.info("获取一级栏目失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "countMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage countMenu(PublicDataVO dataVO) {
        try {
//            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            return ResponseMessage.sendOK(bckjDicMenuService.countMenu());
        } catch (Exception e) {
            log.info("获取菜单总数失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

}