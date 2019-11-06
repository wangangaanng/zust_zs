/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.alibaba.fastjson.JSON;
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
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.service.BckjBizYhxxService;
import com.zghzbckj.manage.utils.SmallAppUtil;
import com.zghzbckj.vo.BckjBizYhxxVo;
import com.zghzbckj.wechat.model.WxXcxUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "bckjBizYhxx")
public class BckjBizYhxxController extends BaseController {


    private static Map<String, Object> dataCenter = Maps.newHashMap();
    @Autowired
    private BckjBizYhxxService bckjBizYhxxService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizYhxxService.findPageBckjBizYhxx(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizYhxx列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 考生报名list
     * @return
     */
    @PostMapping("getZsList")
    @ResponseBody
    public ResponseMessage getZsList(PublicDataVO dataVO){
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizYhxxService.getZsList(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
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
            ResponseMessage data = bckjBizYhxxService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizYhkz列表失败\r\n" + e.getStackTrace()[0], e);
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
            log.error(e + "保存BckjBizYhxx信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizYhxxService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizYhxx\r\n" + e.getStackTrace()[0], e);
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
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "newPassword", "oldPassword", "newPasswordAgain");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            ValidateMsg yhidmsg = ValidateUtils.isEmpty(datamap, "owid");
            if (!yhidmsg.getSuccess()) {
                return ResponseMessage.sendError(2, "登录过期");
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
            return bckjBizYhxxService.getOneByOwid(owid);
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
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "yhDlzh", "yhDlmm", "openid", "unionid", "wxid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizYhxxService.appletLogin(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    @PostMapping("deleteInfo")
    @ResponseBody
    public ResponseMessage deleteInfo(@RequestBody BckjBizYhxx bckjBizYhxx) {
        try {
            bckjBizYhxxService.delete(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    @PostMapping("saveOrALL")
    @ResponseBody
    public ResponseMessage saveOrALL(@RequestBody List<BckjBizYhxxVo> lists) {
        try {
            List<BckjBizYhxx> listYhxx = new ArrayList<>();
            for (BckjBizYhxxVo bckjBizYhxxVo : lists) {
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
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
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping("saveconInfo")
    @ResponseBody
    public ResponseMessage saveconInfo(@RequestBody BckjBizYhxx bckjBizYhxx) {
        try {
            bckjBizYhxxService.saveOrUpdate(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping("insertInfo")
    @ResponseBody
    public ResponseMessage insertInfo(@RequestBody BckjBizYhxx bckjBizYhxx) {
        try {
            bckjBizYhxxService.insertInfo(bckjBizYhxx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 后台根据job 的 owid 获得关注学生信息
     *
     * @param dataVO
     * @param type   0:关注 1：签到 2：报名
     * @return
     */
    @PostMapping("getYhxxInfoList/{type}")
    @ResponseBody
    public ResponseMessage getYhxxInfoList(@PathVariable("type") Integer type, PublicDataVO dataVO) {
        try {
            List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizYhxxService.getYhxxInfoList(type, filterModels, dataVO.getPageSize(), dataVO.getPageNo()));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>方法:swYtzc TODO三位一体注册接口 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 15:54  </li>
     * </ul>
     */
    @RequestMapping(value = "swYtzc", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage swYtzc(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "swZh", "xm", "xb", "yzm", "swMm");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizYhxxService.swYtzc(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("三位一体注册接口失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:swYtLogin TODO三位一体登录 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 17:34  </li>
     * </ul>
     */
    @RequestMapping(value = "swYtLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage swYtLogin(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "swMm", "swZh");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizYhxxService.loginSwty(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("三位一体登录失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:getSwWxInfo TODO获取三位一体微信用户信息</p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 18:02  </li>
     * </ul>
     */
    @RequestMapping(value = "getSwWxInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSwWxInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "code", "iv", "encryptedData", "wxid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            WxXcxUserModel wxUser = SmallAppUtil.getOpenId(dataMap);
            bckjBizYhxxService.swWxinfo(wxUser);
            return ResponseMessage.sendOK(wxUser);
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取微信用户信息失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:forgetPwd TODO忘记密码 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 19:24  </li>
     * </ul>
     */
    @RequestMapping(value = "forgetPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage forgetPwd(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "swMm", "swZh", "yzm");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(bckjBizYhxxService.forgetPwd(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("三位一体密码设置失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    /**
     * 招生考生报名
     */
    @PostMapping("candidatesRegistration")
    @ResponseBody
    public ResponseMessage candidatesRegistration(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "xm", "sjh", "exp9", "qxzy", "yzm", "type");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizYhxxService.candidatesRegistration(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 招生考生报名发送验证码
     *
     * @param dataVO
     * @param type
     * @return
     */
    @PostMapping("sendYzm/{type}")
    @ResponseBody
    public ResponseMessage sendYzm(@PathVariable("type") String type, PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "sjh");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            String resStr="";
            if (type.equals("1")){
                resStr= bckjBizYhxxService.sendBmYzm(dataMap);
            }else if(type.equals("2")){
                resStr = bckjBizYhxxService.sendYyYzm(dataMap);
            }
            return ResponseMessage.sendOK(resStr);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }

    }

    /**
     * 预约校园开放日
     * @param dataVO
     * @return
     */
    @PostMapping("apOfCaOpDay")
    @ResponseBody
    public ResponseMessage apOfCaOpDay(PublicDataVO dataVO){
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "sjh", "yzm");
            if(!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizYhxxService.apOfCaOpDay(dataMap));
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 展示校园开发日页面
     * @return
     */
    @PostMapping("getShowCaOpDayDate")
    @ResponseBody
    public ResponseMessage getShowCaOpDayDate(){
        try {
           return ResponseMessage.sendOK(bckjBizYhxxService.getShowCaOpDayDate()) ;
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }

}