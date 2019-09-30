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
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.service.BckjBizJybmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizJybm")
public class BckjBizJybmController extends BaseController {
    @Autowired
    private BckjBizJybmService bckjBizJybmService;
    private static Map<String, Object> map = new HashMap<>();

    @RequestMapping(value = "/setOwid")
    @ResponseBody
    public void setOwid(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            map.put("jobRefOwid", mapData.get("owid"));
            map.put("bmlx", JyContant.BMLX_QY);
        } catch (Exception e) {
            log.error(e + "失败\r\n" + e.getStackTrace()[0], e);
        }

    }


    @RequestMapping(value = "/setOwidXsbm")
    @ResponseBody
    public void setOwidXsbm(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            map.put("jobRefOwid", mapData.get("owid"));
            map.put("bmlx", JyContant.BMLX_XS);
            map.put("bmdx", JyContant.BMDX_ZW);
        } catch (Exception e) {
            log.error(e + "失败\r\n" + e.getStackTrace()[0], e);
        }
    }
    @RequestMapping(value = "/getListAllZw")
    @ResponseBody
    public ResponseMessage getListAllZw(PublicDataVO dataVO) {
        try {
            map.clear();
            map.put("bmlx", JyContant.BMLX_XS);
            map.put("bmdx", JyContant.BMDX_ZW);
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybm(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "/getListZph")
    @ResponseBody
    public ResponseMessage getListZph(PublicDataVO dataVO) {
        try {
            map.clear();
            map.put("bmlx", JyContant.BMDX_ZPH);
            map.put("bmdx", JyContant.BMLX_QY);
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybm(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybm(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>接口 getXjhList.java : <p>
     * <p>说明：宣讲会列表</p>
     * <pre>
     * @author cc
     * @date 2019/9/20 16:35
     * </pre>
     */
    @RequestMapping(value = "/getXjhList")
    @ResponseBody
    public ResponseMessage getXjhList(PublicDataVO dataVO) {
        try {
            map.clear();
            //宣讲会
            map.put("bmdx", 1);
            //企业对象
            map.put("bmlx", 0);
            map.put("state", 0);
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybmXjh(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "/getXsbmZwList")
    @ResponseBody
    public ResponseMessage getXsbmZwList(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybmZw(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "/getXsbmList")
    @ResponseBody
    public ResponseMessage getXsbmList(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJybmService.findPageBckjBizJybmXjh(filters, dataVO.getPageNo(), dataVO.getPageSize(), map);
        } catch (Exception e) {
            log.error(e + "获取bckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizJybmService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizJybm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizJybmService.saveBckjBizJybm(mapData);
        } catch (Exception e) {
            log.error(e + "保存BckjBizJybm信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizJybmService.getWithJob(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizJybm\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 企业学生 招聘会/宣讲会 报名.java : <p>
     * <p>说明：</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 15:30
     * </pre>
     */
    @RequestMapping(value = "applyJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage applyJob(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "bmlx", "bmdx");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizJybmService.applyJob(mapData);
            if ("true".equals(resultMap.get("result").toString())) {
                return ResponseMessage.sendOK("");
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
            }
        } catch (Exception e) {

            log.error(e + "初始BckjBizQyxx\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>接口 myBmList.java : <p>
     * <p>说明：报名列表</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 15:43
     * </pre>
     */
    @RequestMapping(value = "myBmList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage myBmList(HttpServletResponse response, HttpServletRequest request,
                                    PublicDataVO dataVO) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageNo", "bmlx", "pageSize");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        try {
            return ResponseMessage.sendOK(bckjBizJybmService.myBmList(dataMap));
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "backPassOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage backPassOne(PublicDataVO dataVo) throws Exception {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVo.getData());
        List<String> codes = new ArrayList<String>();
        codes.add(mapData.get("owid").toString());
        //通过 状态为1
        Integer state = 1;
        Map resultMap = bckjBizJybmService.submitPurchaseBack(codes, state, mapData);
        if ("true".equals(resultMap.get("result").toString())) {
            //数据回写
            return ResponseMessage.sendOK(resultMap.get("bean"));
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
        }
    }

    /**
     * 退款审核拒绝
     *
     * @param dataVo
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "backRejectOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage backRejectOne(PublicDataVO dataVo) throws Exception {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVo.getData());
        List<String> codes = new ArrayList<String>();
        codes.add(mapData.get("owid").toString());
        //拒绝 状态为4
        Integer state = 2;
        Map resultMap = bckjBizJybmService.submitPurchaseBack(codes, state, mapData);
        if ("true".equals(resultMap.get("result").toString())) {
            //数据回写
            return ResponseMessage.sendOK(resultMap.get("bean"));
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, resultMap.get("msg").toString());
        }
    }


}