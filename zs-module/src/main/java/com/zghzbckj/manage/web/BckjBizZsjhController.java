/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.google.common.collect.Lists;
import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.service.BckjBizZsjhService;
import com.zghzbckj.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Savepoint;
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
            log.error(e + "获取bckjBizZsjh列表失败\r\n" + e.getStackTrace()[0], e);
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
            log.error(e + "删除BckjBizZsjh列表失败\r\n" + e.getStackTrace()[0], e);
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
            log.error(e + "保存BckjBizZsjh信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }
    @PostMapping("saveOne")
    @ResponseBody
    public ResponseMessage saveOne(PublicDataVO dataVO){
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizZsjhService.saveOne(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizZsjh信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizZsjhService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizZsjh\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
    *<p>方法:getListApi TODO招生计划查询条件数据</p>
    *<ul>
     *<li> @param dataVO TODO</li>
    *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/14 15:03  </li>
    *</ul>
    */
    @RequestMapping(value = "getChanges", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getChanges(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            return ResponseMessage.sendOK(bckjBizZsjhService.getChanges(mapData));
        } catch (Exception e) {
            log.info("获取查询条件数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }
    /**
     * <p>方法:getListApi TODO招生计划获取数据结果</p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/14 15:03  </li>
     * </ul>
     */
    @RequestMapping(value = "getResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getResult(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "pageNo","pageSize");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizZsjhService.getResult(mapData));
        } catch (Exception e) {
            log.info("获取查询条件数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     *<p>功能描述:导出excel表格 exportExcel</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/4 19:40</li>
     *</ul>
     */
    @PostMapping(value = "exportExcel")
    @ResponseBody
    public ResponseMessage exportExcel(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            String fileUrl = bckjBizZsjhService.generateExcel(dataMap);
            return ResponseMessage.sendOK(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }



    /**
     * <p>功能描述:后台录入招生计划信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * </ul>
     */
    @RequestMapping(value = "recordInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizZsjhService.recordInfo(dataMap.get("path").toString());
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }



    /**
     * 得到筛选的list
     * @param dataVO
     * @return
     */
    @PostMapping("getCustomList")
    @ResponseBody
    public ResponseMessage getCustomList(PublicDataVO dataVO){
        List<String> lists = Lists.newArrayList();
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            lists = bckjBizZsjhService.getCustomList(dataMap);
            return ResponseMessage.sendOK(lists);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 后台宣传会报名下拉框
     * dataVO
     * @return
     */
    @PostMapping("getCustomDicList")
    @ResponseBody
    public ResponseMessage getCustomDicList(PublicDataVO dataVO){
        List resList = Lists.newArrayList();
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            resList=bckjBizZsjhService.getCustomDicList(dataMap);
            return ResponseMessage.sendOK(resList);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 后台展示招生宣传信息
     * @param dataVO
     * @return
     */
    @PostMapping("getPropaganda")
    @ResponseBody
    public ResponseMessage getPropaganda(PublicDataVO dataVO){
        try {
            /*Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());*/
            List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
          /*  String owid = CacheUtil.getVal(dataVO.getSessionId());*/
            return ResponseMessage.sendOK(bckjBizZsjhService.getPropaganda(dataMap,dataVO.getPageNo(),dataVO.getPageSize()));

        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }












    /**
     * 展示宣传会list
     * @return
     */
    @PostMapping("getPropagandaQt")
    @ResponseBody
    public ResponseMessage getPropagandaQt(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageSize", "pageNo");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizZsjhService.getPropagandaQt(dataMap,MapUtils.getInt(dataMap,"pageNo"),MapUtils.getInt(dataMap,"pageSize")));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }


}