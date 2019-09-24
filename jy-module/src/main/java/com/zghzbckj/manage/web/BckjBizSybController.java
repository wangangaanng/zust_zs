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
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.service.BckjBizSybService;
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
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "bckjBizSyb")
public class BckjBizSybController extends BaseController {
    @Autowired
    private BckjBizSybService bckjBizSybService;


    @RequestMapping(value = "/getList")
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
            ResponseMessage data = bckjBizSybService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizSyb列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            BckjBizSyb syb=JsonUtil.map2Bean(mapData,BckjBizSyb.class);
            return bckjBizSybService.saveBckjBizSyb(syb);
        } catch (Exception e) {
            log.error(e + "保存BckjBizSyb信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizSybService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizSyb\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveSyxx", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveSyxx(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            BckjBizSyb syb = JsonUtil.map2Bean(mapData, BckjBizSyb.class);
            String validMess = doValid(syb);
            if (!TextUtils.isEmpty(validMess)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validMess);
            }
            //判断id是否为
            return bckjBizSybService.saveBckjBizSyb(syb);
        } catch (Exception e) {
            log.error(e + "保存BckjBizSyb信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    private String doValid(BckjBizSyb syb) {
        if (TextUtils.isEmpty(syb.getKsh())) {
            return "考生号不能为空";
        } else {
            if (syb.getKsh().length() > 30) {
                return "考生号数据不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getSfzh())) {
            return "身份证为空";
        } else {
            if (syb.getSfzh().length() > 30) {
                return "身份证格式错误";
            }
        }
        if (TextUtils.isEmpty(syb.getRxnf())) {
            return "入学年份不能为空";
        } else {
            if (syb.getRxnf().length() > 30) {
                return "入学年份不能为大于30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getCsrq())) {
            return "出生日期不能为空";
        } else {
            if (syb.getCsrq().length() > 30) {
                return "出生日期不能超过30个字符";
            }
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
        } else {
            if (syb.getByrq().length() > 30) {
                return "毕业日期不能超过30个字符";
            }
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

        if (TextUtils.isEmpty(syb.getSsxx())) {
            return "所属学校不能为空";
        } else {
            if (syb.getSsxx().length() > 100) {
                return "所属学校不能超过100个字符";
            }
        }

        if (TextUtils.isEmpty(syb.getSsxy())) {
            return "所属学院不能为空";
        } else {
            if (syb.getSsxy().length() > 100) {
                return "所属学院不能超过100个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getXxzy())) {
            return "学校专业不能为空";
        } else {
            if (syb.getXxzy().length() > 100) {
                return "学校专业不能超过100个字符";
            }
        }
        if (!TextUtils.isEmpty(syb.getZyfx()) && syb.getZyfx().length() > 100) {
            return "专业方向不能超过100个字符";
        }
        if (TextUtils.isEmpty(syb.getSzbj())) {
            return "所在班级不能为空";
        } else {
            if (syb.getSzbj().length() > 100) {
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
        if (TextUtils.isEmpty(syb.getSjhm())) {
            return "手机号码不能为空";
        } else {
            if (syb.getSjhm().length() > 30) {
                return "手机号码不能超过30个字符";
            }
        }
        if (TextUtils.isEmpty(syb.getDzyx())) {
            return "电子邮箱不能为空";
        } else {
            if (syb.getDzyx().length() > 30) {
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


}