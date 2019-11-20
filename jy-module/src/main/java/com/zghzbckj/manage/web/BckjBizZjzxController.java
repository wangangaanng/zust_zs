/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.google.common.collect.Maps;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjDicKeysSer;
import com.zghzbckj.manage.service.BckjBizZjzxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ccController
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizZjzx")
public class BckjBizZjzxController extends BaseController {
	@Autowired
	private BckjBizZjzxService bckjBizZjzxService;
    @Autowired
    BckjDicKeysSer bckjDicKeysSer;

	@RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
    return bckjBizZjzxService.findPageBckjBizZjzx(filters, dataVO.getPageNo(), dataVO.getPageSize());
    } catch (Exception e) {
    log.error(e+"获取bckjBizZjzx列表失败\r\n"+e.getStackTrace()[0] , e);
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
            ResponseMessage data = bckjBizZjzxService.removeOrder(codes);
            return data;
            } catch (Exception e) {
            log.error(e+"删除BckjBizZjzx列表失败\r\n" +e.getStackTrace()[0] , e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }

            @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage saveInfo(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizZjzxService.saveBckjBizZjzx(mapData);
            } catch (Exception e) {
            log.error(e+"保存BckjBizZjzx信息失败\r\n"+e.getStackTrace()[0]  , e);
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
            return ResponseMessage.sendOK(bckjBizZjzxService.get(mapData.get("owid").toString()));
            } catch (Exception e) {

            log.error(e+"初始BckjBizZjzx\r\n" +e.getStackTrace()[0] ,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }
        /**
         * <p>功能描述:咨询导师列表展示</p >
         * <ul>
         * <li>@param </li>
         * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
         * <li>@throws </li>
         * <li>@author wangangaanng</li>
         * <li>@date 2019/9/11</li>
         * </ul>
         */
            @RequestMapping(value = "supervisorList",method=RequestMethod.POST)
            @ResponseBody
            public ResponseMessage supervisorList(PublicDataVO dataVO){
                try {
                    Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
                    ValidateMsg msg= ValidateUtils.isEmpty(dataMap,"pageSize","pageNo");
                    if(!msg.getSuccess()){
                        return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
                    }
                    return bckjBizZjzxService.supervisorList(dataMap);
                }
                catch (Exception e){
                    log.error(CommonConstant.ERROR_MESSAGE,e);
                    return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
                }
            }

    /**
     * <p>功能描述:显示老师详情</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11</li>
     * </ul>
     */
    @PostMapping("details")
    @ResponseBody
    public ResponseMessage details(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizZjzxService.get(dataMap.get("owid").toString()));
        }
        catch (Exception e ){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:显示学生咨询列表(未回复)</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11</li>
     * </ul>
     */
    @PostMapping("showStudentReplyList")
    @ResponseBody
    public ResponseMessage showStudentReplyList(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "state","pageSize","pageNo","zxlx");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            ValidateMsg yhid = ValidateUtils.isEmpty(dataMap, "yhid");
            if(!yhid.getSuccess()){
                return ResponseMessage.sendError(2,"登入过期");
            }
            return bckjBizZjzxService.showStudentReplyList(dataMap);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>功能描述:回复咨询</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11</li>
     * </ul>
     */
    @PostMapping("replyConsult")
    @ResponseBody
    public ResponseMessage replyConsult(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap= JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid","danr","zxlx");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            ValidateMsg yhid = ValidateUtils.isEmpty(dataMap, "yhid");
            if(!yhid.getSuccess()){
                return  ResponseMessage.sendError(2,"登入过期");
            }
            //关键字过滤map
            HashMap<String, Object> filterMap = Maps.newHashMap();
            filterMap.put("content",dataMap.get("danr").toString());
            ResponseMessage responseMessage = bckjDicKeysSer.jyKeyFilter(filterMap);
            if(!TextUtils.isEmpty(responseMessage)&&responseMessage.getBackCode()==0){
                if(!TextUtils.isEmpty(responseMessage.getBean())){
                    return ResponseMessage.sendError(ResponseMessage.FAIL,responseMessage.getBean().toString());
                }
            }
            return bckjBizZjzxService.replyConsult(dataMap);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>功能描述:后台录入专家信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/25</li>
     * </ul>
     */
    @RequestMapping(value = "recordInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordInfo( PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return bckjBizZjzxService.  recordInfo(dataMap.get("path").toString());
        } catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

        /**
         * <p>功能描述:后台显示专家信息</p >
         * <ul>
         * <li>@param </li>
         * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
         * <li>@throws </li>
         * <li>@author wangangaanng</li>
         * <li>@date 2019/9/25</li>
         * </ul>
         */
        @PostMapping("showInfoList")
        @ResponseBody
        public  ResponseMessage showInfoList(PublicDataVO dataVO){
            try {
                List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
                return bckjBizZjzxService.showInfoList(filters,dataVO.getPageNo(),dataVO.getPageSize());
            }
            catch (Exception e){
                log.error(CommonConstant.ERROR_MESSAGE,e);
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
            }
        }

    /**
     * <p>功能描述:前台显示专家信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/25</li>
     * </ul>
     */
    @PostMapping("showInfoListQt")
    @ResponseBody
    public  ResponseMessage showInfoListQt(PublicDataVO dataVO){
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizZjzxService.showInfoListQt(filters,dataVO.getPageNo(),dataVO.getPageSize());
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }



    /**
     * <p>功能描述:后台保存专家信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/25</li>
     * </ul>
     */
    @PostMapping("saveConsultInfo")
    @ResponseBody
    public ResponseMessage saveConsultInfo(PublicDataVO dataVO){
        try{
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "dataList");
            if(((ArrayList)dataMap.get("dataList")).size()==0){
                return ResponseMessage.sendOK("无更新数据");
            }
            List<Map<String, Object>> components = (List)dataMap.get("dataList");
            if(components.size()==0){
                return ResponseMessage.sendOK("无更新数据");
            }
            return bckjBizZjzxService.saveConsultInfo(components);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }

    }



    /**
     * <p>功能描述:新建专家信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/23</li>
     * </ul>
     */
    @PostMapping("insertssInfo")
    @ResponseBody
    public ResponseMessage insertssInfo(PublicDataVO dataVO){
        try{
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizZjzxService.insertssInfo(dataMap);
        }
        catch (Exception e)
        {
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:得到专家详情信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/23</li>
     * </ul>
     */
    @PostMapping("getConsultsOne")
    @ResponseBody
    public ResponseMessage getConsultsOne(PublicDataVO dataVO){
        try{
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return bckjBizZjzxService.getConsultsOne(dataMap);
        }
        catch (Exception e)
        {
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 获得字典表中专家回复的天数
     * @return
     */
    @PostMapping("getConsultsReplyDay")
    @ResponseBody
    public ResponseMessage getConsultsReplyDay(){
        try {
            return  ResponseMessage.sendOK(bckjBizZjzxService.getConsultsReplyDay());
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }

    }
}