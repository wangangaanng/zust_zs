/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.alibaba.fastjson.JSON;
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
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.service.BckjBizYhxxService;

import com.zghzbckj.util.MapUtil;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Text;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * ccController
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizYhxx")
public class BckjBizYhxxController extends BaseController {



	@Autowired
	private BckjBizYhxxService bckjBizYhxxService;


	@RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
    return bckjBizYhxxService.findPageBckjBizYhxx(filters, dataVO.getPageNo(), dataVO.getPageSize());
    } catch (Exception e) {
    log.error(e+"获取bckjBizYhxx列表失败\r\n"+e.getStackTrace()[0] , e);
    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
    }
    }

            @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
            @ResponseBody
            public ResponseMessage saveInfo(PublicDataVO dataVO) {
            try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizYhxxService.saveBckjBizYhxx(mapData);
            } catch (Exception e) {
            log.error(e+"保存BckjBizYhxx信息失败\r\n"+e.getStackTrace()[0]  , e);
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
            return ResponseMessage.sendOK(bckjBizYhxxService.get(mapData.get("owid").toString()));
            } catch (Exception e) {

            log.error(e+"初始BckjBizYhxx\r\n" +e.getStackTrace()[0] ,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
            }
            }




    /**
     * <p>功能描述:学生登入</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */

    @ResponseBody
    @RequestMapping(value = "logIn", method = RequestMethod.POST)
    public ResponseMessage logIn(PublicDataVO dataVO) {
        try {
            Map<String, Object> datamap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "yhDlzh", "yhDlmm");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizYhxxService.logIn(datamap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>功能描述:更改密码</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */
    @ResponseBody
    @RequestMapping(value = "modfiyPassword", method = RequestMethod.POST)
    public ResponseMessage modfiyPassword(PublicDataVO dataVO) {
        try {
            Map<String, Object> datamap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "newPassword", "oldPassword","newPasswordAgain");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            ValidateMsg yhidmsg = ValidateUtils.isEmpty(datamap, "owid");
            if(!yhidmsg.getSuccess()){
                return ResponseMessage.sendError(2,"登录过期");
            }
            return bckjBizYhxxService.modfiyPassword(datamap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:根据owid查询用户信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */

    @PostMapping(value = "getOneByOwid")
    @ResponseBody
    public ResponseMessage getOneByOwid(@RequestParam("owid") String owid) {
        try {
            if (ValidateUtils.isEmpty(owid)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_NOPARAMS);
            }
            return  bckjBizYhxxService.getOneByOwid(owid);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:学生小程序登入</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/17</li>
     * </ul>
     */
    @PostMapping(value = "appletLogin")
    @ResponseBody
    public ResponseMessage appletLogin(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "yhDlzh","yhDlmm","openid","unionid","wxid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizYhxxService.appletLogin(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:后台录入师生信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/20</li>
     * </ul>
     */
    @RequestMapping(value = "recordInfo/{state}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordInfo(@PathVariable("state") Integer olx,PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            //如果为学生录入
            if (olx == 0) {
                return bckjBizYhxxService.recordStudentInfo(dataMap.get("path").toString());
            }
            //如果为老师录入
            if (olx == 1) {
                return ResponseMessage.sendOK("");
            }
                return ResponseMessage.sendOK("");
        } catch (RepeatException e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendOK("excel表中存在学号\" + i + \"重复录入,导入失败");
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:后台显示师生信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/20</li>
     * </ul>
     */
    @PostMapping("showInfoList/{state}")
    @ResponseBody
    public ResponseMessage showInfoList(@PathVariable("state") Integer state,PublicDataVO dataVO){
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            //显示学生信息列表
            if(state==0){
                return ResponseMessage.sendOK(bckjBizYhxxService.showInfoList(filters,dataVO.getPageNo(),dataVO.getPageSize()));
            }
            return  ResponseMessage.sendOK("");
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    /**
     * <p>功能描述:新建或修改学生信息</p >
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
            return bckjBizYhxxService.insertssInfo(dataMap);
        }
        catch (Exception e)
        {
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }

    }

    @PostMapping("saveOrALL")
    @ResponseBody
    public ResponseMessage saveOrALL(@RequestBody List<BckjBizYhxxVo> lists){
        try{
            List<BckjBizYhxx> listYhxx=new ArrayList<>();
            for(BckjBizYhxxVo bckjBizYhxxVo:lists){
                BckjBizYhxx bckjBizYhxx=new BckjBizYhxx();
                bckjBizYhxx.setOwid(bckjBizYhxxVo.getOwid());
                bckjBizYhxx.setXm(bckjBizYhxxVo.getXm());
                bckjBizYhxx.setYhDlzh(bckjBizYhxxVo.getYhDlzh());
                bckjBizYhxx.setYhDlmm(bckjBizYhxxVo.getYhDlmm());
                bckjBizYhxx.setXm(bckjBizYhxxVo.getXm());
                bckjBizYhxx.setYx(bckjBizYhxxVo.getYx());
                bckjBizYhxx.setXb(bckjBizYhxxVo.getXb());
                bckjBizYhxx.setYhlx(bckjBizYhxxVo.getYhlx());
                listYhxx.add(bckjBizYhxx);

            }
            bckjBizYhxxService.saveAll(listYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping("deleteInfo")
    @ResponseBody
    public  ResponseMessage deleteInfo(@RequestBody BckjBizYhxx bckjBizYhxx){
        try {
            bckjBizYhxxService.delete(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping("saveconInfo")
    @ResponseBody
    public  ResponseMessage saveconInfo(@RequestBody BckjBizYhxx bckjBizYhxx){
        try {
            bckjBizYhxxService.saveOrUpdate(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }



    @PostMapping("insertInfo")
    @ResponseBody
    public  ResponseMessage insertInfo(@RequestBody BckjBizYhxx bckjBizYhxx){
        try {
            bckjBizYhxxService.insertInfo(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 后台进入修改页面读取出信息
     * @param
     * @return
     */
    @PostMapping("getStudentOne")
    @ResponseBody
    public  ResponseMessage getStudentOne(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizYhxxService.getStudentOne(dataMap));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

   /* *//**
     * 后台删除list
     * @param
     * @return
     *//*
    @PostMapping("deleteAllList")
    @ResponseBody
    public ResponseMessage deleteAllList(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
             bckjBizYhxxService.deleteAllList(dataMap);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    */

    /**
     * 后台删除gridlist
     * @param dataVO
     * @return ResponseMessage
     */
    @PostMapping("deleteInfoList")
    @ResponseBody
    public ResponseMessage deleteInfoList(PublicDataVO dataVO){
        try {
            List<Object> list = JsonUtil.jsonToList(dataVO.getData());
            List<String> owidcodes = new ArrayList<String>(list.size());
            List<String> xsxhcodes = new ArrayList<String>(list.size());
            for (Object obj : list) {
                owidcodes.add(((Map<String, Object>) obj).get("owid").toString());
                xsxhcodes.add(((Map<String, Object>) obj).get("xsxh").toString());
            }
            return   ResponseMessage.sendOK(bckjBizYhxxService.deleteInfoList(owidcodes,xsxhcodes));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return  ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
    @PostMapping("getYhxxInfo")
    @ResponseBody
    public ResponseMessage getYhxxInfo(PublicDataVO dataVO){


return null;

    }

}