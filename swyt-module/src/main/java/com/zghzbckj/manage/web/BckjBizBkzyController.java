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
import com.zghzbckj.manage.entity.BckjBizBkzy;
import com.zghzbckj.manage.service.BckjBizBkzyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * ccController
 *
 * @author cc
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "bckjBizBkzy")
public class BckjBizBkzyController extends BaseController {
    @Autowired
    private BckjBizBkzyService bckjBizBkzyService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizBkzyService.findPageBckjBizBkzy(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(e + "获取bckjBizBkzy列表失败\r\n" + e.getStackTrace()[0], e);
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
            List data = bckjBizBkzyService.removeOrder(codes);
            return ResponseMessage.sendOK(data);
        } catch (Exception e) {
            log.error(e + "删除BckjBizBkzy列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = {"removeDepat"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseMessage removeDepat(HttpServletRequest request, PublicDataVO dataVo) {
        Map dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, new String[]{"owid"});
        if (!msg.getSuccess().booleanValue()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL.intValue(), msg.toString());
        } else {
            List<String> owids = new ArrayList<>();
            owids.add(dataMap.get("owid").toString());
            bckjBizBkzyService.removeOrder(owids);
            return ResponseMessage.sendOK(owids);
        }
    }


    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return ResponseMessage.sendOK(bckjBizBkzyService.saveBckjBizBkzy(mapData));
        } catch (Exception e) {
            log.error(e + "保存BckjBizBkzy信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizBkzyService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizBkzy\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>方法:getMajors TODO 获取专业列表</p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 16:20  </li>
     * </ul>
     */
    @RequestMapping(value = "getMajors", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getMajors(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "xxbh", "fid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBkzyService.getListByCj(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取专业列表：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage test(PublicDataVO dataVO) {
        try {

            return ResponseMessage.sendOK(9999);
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取营业日志失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = {"listTree"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseMessage listTree(HttpServletRequest request, PublicDataVO data) {
        List filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(bckjBizBkzyService.listTree(filters));
    }


    @RequestMapping(value = {"updateMoveDepart"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseMessage updateMoveMenu(HttpServletRequest request, PublicDataVO data) {
        List datas = JsonUtil.jsonToList(data.getData(), Map.class);
        ArrayList bckjBizBkzyList = new ArrayList(datas.size());
        Iterator i$ = datas.iterator();

        while (i$.hasNext()) {
            Map menuData = (Map) i$.next();
            BckjBizBkzy depat = (BckjBizBkzy) JsonUtil.map2Bean(menuData, BckjBizBkzy.class);
            bckjBizBkzyList.add(depat);
        }

        if (null != bckjBizBkzyList && bckjBizBkzyList.size() > 0) {
            this.bckjBizBkzyService.saveOrUpdateAll(bckjBizBkzyList);
            return ResponseMessage.sendOK((Object) null);
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL.intValue(), "");
        }
    }

    @RequestMapping(value = "listByDl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listByDl(PublicDataVO publicDataVO) {
        Map params = Maps.newHashMap();
        params.put("cc", 1);
        List<BckjBizBkzy> buys = bckjBizBkzyService.findListByParams(params, " a.px ");
        if (buys == null || buys.size() == 0) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BckjBizBkzy buy : buys) {
            Map temp = new HashMap();
            temp.put("label", buy.getName());
            temp.put("value", buy.getName());
            maps.add(temp);
        }
        return ResponseMessage.sendOK(maps);
    }

    @RequestMapping(value = "listByBk", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listByBk(PublicDataVO publicDataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(publicDataVO.getData());
        Map params = Maps.newHashMap();
        params.put("cc", 2);
        if (!TextUtils.isEmpty(dataMap.get("dicName"))) {
            Map temp = Maps.newHashMap();
            temp.put("name", dataMap.get("dicName").toString());
            BckjBizBkzy bzkz = bckjBizBkzyService.findOneByParams(temp, "");
            if (!TextUtils.isEmpty(bzkz)) {
                params.put("fid", bzkz.getOwid());
            }
        }
        List<BckjBizBkzy> buys = bckjBizBkzyService.findListByParams(params, " a.px ");
        if (buys == null || buys.size() == 0) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BckjBizBkzy buy : buys) {
            Map temp = new HashMap();
            temp.put("label", buy.getName());
            temp.put("value", buy.getName());
            maps.add(temp);
        }
        return ResponseMessage.sendOK(maps);
    }

    @RequestMapping(value = "listByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listByName(PublicDataVO publicDataVO) {
        Map params = Maps.newHashMap();
        params.put("cc", 2);
        List<BckjBizBkzy> buys = bckjBizBkzyService.findListByParams(params, " a.px ");
        if (buys == null || buys.size() == 0) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BckjBizBkzy buy : buys) {
            Map temp = new HashMap();
            temp.put("label", buy.getName());
            temp.put("value", buy.getOwid());
            maps.add(temp);
        }
        return ResponseMessage.sendOK(maps);
    }

    @RequestMapping(value = "listByZy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listByZy(PublicDataVO publicDataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(publicDataVO.getData());
        Map params = Maps.newHashMap();
        params.put("cc", 3);
        if (!TextUtils.isEmpty(dataMap.get("dicName"))) {
            Map temp = Maps.newHashMap();
            temp.put("name", dataMap.get("dicName").toString());
            BckjBizBkzy bzkz = bckjBizBkzyService.findOneByParams(temp, "");
            if (!TextUtils.isEmpty(bzkz)) {
                params.put("fid", bzkz.getOwid());
            }
        }
        List<BckjBizBkzy> buys = bckjBizBkzyService.findListByParams(params, " a.px ");
        if (buys == null || buys.size() == 0) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BckjBizBkzy buy : buys) {
            Map temp = new HashMap();
            temp.put("label", buy.getName());
            temp.put("value", buy.getName());
            maps.add(temp);
        }
        return ResponseMessage.sendOK(maps);
    }

    @RequestMapping(value = "getNumber", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getNumber(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "dl");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizBkzyService.getNumber(mapData.get("dl").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizBkzy\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}