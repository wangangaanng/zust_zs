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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.service.BckjBizJyschemeService;
import com.zghzbckj.manage.service.BckjBizYhxxService;
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
 * BckjBizYhxxService
 * 就业方案Controller
 *
 * @author wangangaanng
 * @version 2019-09-30
 */
@Controller
@RequestMapping(value = "bckjBizJyscheme")
public class BckjBizJyschemeController extends BaseController {
    @Autowired
    private BckjBizJyschemeService bckjBizJyschemeService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;


    /**
     * <p>功能描述:保存学生填写的就业方案</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/30</li>
     * </ul>
     */
    @PostMapping("saveJyFaInfo")
    @ResponseBody
    public ResponseMessage saveJyFaInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizJyschemeService.saveJyFaInfo(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:获取学生填写的就业方案的信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/30</li>
     * </ul>
     */
    @PostMapping("getJyBaseInfo")
    @ResponseBody
    public ResponseMessage getJyBaseInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(2, "登入过期");
            }
            return bckjBizJyschemeService.getJyBaseInfo(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 后台详情页面getone 根据 owid
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
            return ResponseMessage.sendOK(bckjBizJyschemeService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizSyb\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 导入单位所在地字典表 导入
     *
     * @param dataVO
     * @return
     */
    @RequestMapping("dqRecordInfo")
    @ResponseBody
    public ResponseMessage dqRecordInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.recordInfo(dataMap.get("path").toString()));

        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:后台录入就业方案信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/24</li>
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
            return bckjBizJyschemeService.recordJyInfo(dataMap.get("path").toString());
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>功能描述:后台显示就业方案列表</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/24</li>
     * </ul>
     */
    @RequestMapping(value = "getJySchemeList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getJySchemeList(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return ResponseMessage.sendOK(bckjBizJyschemeService.showInfoList(filters, dataVO.getPageNo(), dataVO.getPageSize()));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 后台进入修改页面读取出一条信息
     *
     * @param
     * @return
     */
    @PostMapping("getOneJyscheme")
    @ResponseBody
    public ResponseMessage getOneJyscheme(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.getOneJyscheme(dataMap));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 前台进入就业方案读取出一条信息
     *
     * @param
     * @return
     */
    @PostMapping("getOneJyschemeQt")
    @ResponseBody
    public ResponseMessage getOneJyschemeQt(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.USER_RELOGIN);
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.getOneJyschemeQt(dataMap));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 获得就业方案信息  小程序
     *
     * @param dataVO
     * @return
     */
    @RequestMapping("getOneJyschemeXcx")
    @ResponseBody
    public ResponseMessage getOneJyschemeXcx(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.USER_RELOGIN);
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.getOneJyschemeXcx(dataMap));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 查询档案信息
     *
     * @param dataVO
     * @return queryDocument
     */
    @PostMapping("queryDocument")
    @ResponseBody
    public ResponseMessage queryDocument(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "xsxm", "sfz");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map<String, Object> resMap = bckjBizYhxxService.queryDocument(dataMap);
            if (TextUtils.isEmpty(resMap)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "无法查找此生源");
            }
            BckjBizJyscheme oneJyschemeQt = bckjBizJyschemeService.queryDocument(dataMap);
            if (TextUtils.isEmpty(oneJyschemeQt)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL,"未找到你的档案信息");
            } else {
                return ResponseMessage.sendOK(oneJyschemeQt);
            }
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }

    }


    /**
     * 后台jyscheme删除gridlist
     *
     * @param dataVO
     * @return ResponseMessage
     */
    @PostMapping("deleteJyList")
    @ResponseBody
    public ResponseMessage deleteJyList(PublicDataVO dataVO) {
        try {
            List<Object> list = JsonUtil.jsonToList(dataVO.getData());
            List<String> xsxhcodes = new ArrayList<String>(list.size());
            List<String> owidcodes = new ArrayList<String>(list.size());
            for (Object obj : list) {
                xsxhcodes.add(((Map<String, Object>) obj).get("xsxh").toString());
                owidcodes.add(((Map<String, Object>) obj).get("owid").toString());
            }
            return ResponseMessage.sendOK(bckjBizJyschemeService.deleteJyList(xsxhcodes, owidcodes));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>功能描述:新建或修改保存就业方案信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/24</li>
     * </ul>
     */
    @PostMapping("insertssInfo")
    @ResponseBody
    public ResponseMessage insertssInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizJyschemeService.insertssInfo(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 前台保存就业方案
     *
     * @param dataVO
     * @return ResponseMessage
     */
    @PostMapping("insertssInfoQt")
    @ResponseBody
    public ResponseMessage insertssInfoQt(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizJyschemeService.insertssInfoQt(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * 保存就业方案信息   小程序
     *
     * @param dataVO
     * @return
     */
    @PostMapping("saveOneJyschemeXcx")
    @ResponseBody
    public ResponseMessage saveOneJyschemeXcx(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            return bckjBizJyschemeService.saveOneJyschemeXcx(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


}