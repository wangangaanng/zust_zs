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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.service.BckjBizSybService;
import com.zghzbckj.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "bckjBizSyb")
public class BckjBizSybController extends BaseController {
    @Autowired
    private BckjBizSybService bckjBizSybService;

    /**
     * 后台展示生源list
     * @param dataVO
     * @return List<bckjbizsyb>
     */
    @PostMapping(value = "getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizSybService.findPageBckjBizSyb(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizSyb列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }



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
            List<String> sfzcodes = new ArrayList<String>(list.size());
            for (Object obj : list) {
                owidcodes.add(((Map<String, Object>) obj).get("owid").toString());
                sfzcodes.add(((Map<String, Object>) obj).get("sfz").toString());
            }
            return   ResponseMessage.sendOK(bckjBizSybService.deleteInfoList(owidcodes));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return  ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }



    /**
     * 后台展示一条学生生源信息
     * @param dataVO
     * @return ResponseMessage
     */
    @RequestMapping(value = "getOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOne(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizSybService.getOne(mapData.get("owid").toString()));
        } catch (Exception e) {
            log.error(e + "初始BckjBizSyb\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }



    /**
     * 后台展示一条学生生源信息
     * @param dataVO
     * @return ResponseMessage
     */
    @RequestMapping(value = "getOneQt", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOneQt(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizSybService.getOneQt(mapData.get("owid").toString()));
        } catch (Exception e) {
            log.error(e + "初始BckjBizSyb\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:新建或修改学生信息</p >
     * <ul>z
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/23</li>
     * </ul>
     */
    @PostMapping("insertssInfo")
    @ResponseBody
    public ResponseMessage insertssInfo(PublicDataVO dataVO){
        try{
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            BckjBizSyb bckjBizSyb = new BckjBizSyb();
            MapUtil.easySetByMap(dataMap,bckjBizSyb);
            String valid = doValid(bckjBizSyb);
            if(!TextUtils.isEmpty(valid)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,valid);
            }
            return bckjBizSybService.insertssInfo(dataMap);
        }
        catch (Exception e)
        {
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 前台保存生源
     * @param dataVO
     * @return ResponseMessage
     */
    @PostMapping("insertssInfoQt")
    @ResponseBody
    public ResponseMessage insertssInfoQt(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
            }
            return  bckjBizSybService.insertssInfoQt(dataMap);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }




    private String doValid(BckjBizSyb syb) {
        if (TextUtils.isEmpty(syb.getXsxh())) {
            return "学号不能为空";
        } else {
            if (syb.getXsxh().length() > 30) {
                return "学号不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getKsh())) {
            return "考生号不能为空";
        } else {
            if (syb.getKsh().length() > 30) {
                return "考生号数据不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getSfz())) {
            return "身份证为空";
        } /*else {
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if (!syb.getSfz().matches(regex)) {
                return "身份证格式错误";
            }*/
      /*  }*/
        if (TextUtils.isEmpty(syb.getRxnf())) {
            return "入学日期不能为空";
        }
        if (TextUtils.isEmpty(syb.getCsrq())) {
            return "出生日期不能为空";
        }
        if (TextUtils.isEmpty(syb.getSyd())) {
            return "生源地不能为空";
        } else {
            if (syb.getSyd().length() > 100) {
                return "生源地不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getMz())) {
            return "民族不能为空";
        } else {
            if (syb.getMz().length() > 30) {
                return "民族不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getZzmm())) {
            return "政治面貌不能为空";
        } else {
            if (syb.getZzmm().length() > 30) {
                return "政治面貌不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getBynf())) {
            return "毕业年份不能为空";
        } else {
            if (syb.getBynf().length() > 30) {
                return "毕业年份不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getByrq())) {
            return "毕业日期不能为空";
        }
        if (TextUtils.isEmpty(syb.getCxsy())) {
            return "城乡生源不能为空";
        } else {
            if (syb.getCxsy().length() > 30) {
                return "城乡生源不能超过30个字符";
            }
        }
        if (!TextUtils.isEmpty(syb.getXqda()) && syb.getXqda().length() > 100) {
            return "入学前档案所在单位不能超过100个字符";
        }
        if (!TextUtils.isEmpty(syb.getHkpcs()) && syb.getHkpcs().length() > 100) {
            return "入学前户口所在地派出所不能超过100个字符";
        }
        if (TextUtils.isEmpty(syb.getXlcc())) {
            return "学历层次不能为空";
        } else {
            if (syb.getXlcc().length() > 100) {
                return "学历层次不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getXz())) {
            return "学制不能为空";
        } else {
            if (syb.getXz().length() > 100) {
                return "学制不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getXxmc())) {
            return "所属学校不能为空";
        } else {
            if (syb.getXxmc().length() > 100) {
                return "所属学校不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getXsxy())) {
            return "所属学院不能为空";
        } else {
            if (syb.getXsxy().length() > 100) {
                return "所属学院不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getXszy())) {
            return "学校专业不能为空";
        } else {
            if (syb.getXszy().length() > 100) {
                return "学校专业不能超过100个字符";
            }
        }
        if (!TextUtils.isEmpty(syb.getZyfx()) && syb.getZyfx().length() > 100) {
            return "专业方向不能超过100个字符";
        }
        if (TextUtils.isEmpty(syb.getXsbj())) {
            return "所在班级不能为空";
        } else {
            if (syb.getXsbj().length() > 100) {
                return "所在班级不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getPyfs())) {
            return "培养方式不能为空";
        } else {
            if (syb.getPyfs().length() > 100) {
                return "培养方式不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getWpdw())) {
            return "委培单位不能为空";
        } else {
            if (syb.getWpdw().length() > 100) {
                return "委培单位不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getKnslb())) {
            return "困难生类别不能为空";
        } else {
            if (syb.getKnslb().length() > 100) {
                return "困难生类别不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getSfslb())) {
            return "师范生类别不能为空";
        } else {
            if (syb.getSfslb().length() > 100) {
                return "师范生类别不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getSjh())) {
            return "手机号码不能为空";
        } else {
            if (syb.getSjh().length() > 30) {
                return "手机号码不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getYx())) {
            return "电子邮箱不能为空";
        } else {
            if (syb.getYx().length() > 30) {
                return "电子邮箱不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getQqhm())) {
            return "qq不能为空";
        } else {
            if (syb.getQqhm().length() > 30) {
                return "qq不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getJtdh())) {
            return "家庭电话不能为空";
        } else {
            if (syb.getJtdh().length() > 30) {
                return "家庭电话不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getJtyb())) {
            return "家庭邮编不能为空";
        } else {
            if (syb.getJtyb().length() > 30) {
                return "家庭邮编不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getJtdz())) {
            return "家庭地址不能为空";
        } else {
            if (syb.getJtdz().length() > 100) {
                return "家庭地址不能超过100个字符";
            }
        }
        return null;
    }




    /**
     * <p>功能描述:后台录入学生信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/23</li>
     * </ul>
     */
    @RequestMapping(value = "recordInfo/{state}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordInfo(@PathVariable("state") Integer olx, PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            //如果为学生录入
            if (olx == 0) {
                return bckjBizSybService.recordStudentInfo(dataMap.get("path").toString());
            }
            //如果为老师录入
            if (olx == 1) {
                return ResponseMessage.sendOK("");
            }
            return ResponseMessage.sendOK("");
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }






}