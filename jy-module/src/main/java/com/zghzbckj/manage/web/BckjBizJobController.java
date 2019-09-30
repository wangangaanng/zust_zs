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
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.service.BckjBizJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "bckjBizJob")
public class BckjBizJobController extends BaseController {
    @Autowired
    private BckjBizJobService bckjBizJobService;

    /**
     * <p>接口 getXjhList.java : <p>
     * <p>说明：宣讲会列表 1待举办  2已举办</p>
     * <pre>
     * @author cc
     * @date 2019/9/30 9:54
     * </pre>
     */
    @RequestMapping(value = "/getXjhList/{state}")
    @ResponseBody
    public ResponseMessage getXjhList(@PathVariable("state") Integer state, PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJobService.findPageBckjBizJobXjh(filters, state, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizJob列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "/getList/{zwlx}")
    @ResponseBody
    public ResponseMessage getListApi(@PathVariable("zwlx") Integer zwlx, PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJobService.findPageBckjBizJob(filters, zwlx, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizJob列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizJobService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizJob列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>接口 deleteOneJob.java : <p>
     * <p>说明：删除职位</p>
     * <pre>
     * @author cc
     * @date 2019/9/19 11:57
     * </pre>
     */
    @PostMapping(value = "deleteOneJob")
    @ResponseBody
    public ResponseMessage deleteOneJob(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            List<String> codes = new ArrayList<String>();
            codes.add(mapData.get("owid").toString());
            ResponseMessage data = bckjBizJobService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizJob列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "saveInfo/{zwlx}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(@PathVariable("zwlx") Integer zwlx, PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为

            return bckjBizJobService.saveBckjBizJob(mapData, zwlx);
        } catch (Exception e) {
            log.error(e + "保存BckjBizJob信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizJobService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizJob\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 getOneJob.java : <p>
     * <p>说明：职位详情</p>
     * <pre>
     * @author cc
     * @date 2019/9/16 15:06
     * </pre>
     */
    @RequestMapping(value = "getOneJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOneJob(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(bckjBizJobService.getOneJob(mapData));
        } catch (Exception e) {

            log.error(e + "初始BckjBizJob\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "fixJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage fixJob(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizJobService.fixJob(mapData);
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
     * <p>接口 addOneJob.java : <p>
     * <p>说明：新增职位</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 14:42
     * </pre>
     */

    @RequestMapping(value = "addOneJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addOneJob(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "qyxxRefOwid", "zwbt");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            Map resultMap = bckjBizJobService.addOneJob(mapData);
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
     * <p>接口 myJobList.java : <p>
     * <p>说明：招聘信息列表</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 14:50
     * </pre>
     */
    @RequestMapping(value = "myJobList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage myJobList(HttpServletResponse response, HttpServletRequest request,
                                     PublicDataVO dataVO) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageNo", "zwlx", "pageSize");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        try {
            return ResponseMessage.sendOK(bckjBizJobService.myJobList(dataMap));
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 firstJobList.java : <p>
     * <p>说明：首页职位文章列表</p>
     * <pre>
     * @author cc
     * @date 2019/9/18 11:20
     * </pre>
     */
    @RequestMapping(value = "firstJobList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage firstJobList(HttpServletResponse response, HttpServletRequest request,
                                        PublicDataVO dataVO) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageNo", "zwlx", "pageSize");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        try {
            return ResponseMessage.sendOK(bckjBizJobService.firstJobList(dataMap));
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * <p>接口 jobByMonth.java : <p>
     * <p>说明：首页日历</p>
     * <pre>
     * @author cc
     * @date 2019/9/12 15:52
     * </pre>
     */
    @RequestMapping(value = "jobByMonth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage jobByMonth(HttpServletResponse response, HttpServletRequest request,
                                      PublicDataVO dataVO) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "startTime", "endTime");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        try {
            return ResponseMessage.sendOK(bckjBizJobService.jobByMonth(dataMap));
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    /**
     * <p>接口 setStop.java : <p>
     * <p>说明：下架职位</p>
     * <pre>
     * @author cc
     * @date 2019/9/19 15:16
     * </pre>
     */
    @RequestMapping(value = "setStop", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage setStop(PublicDataVO publicDataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(publicDataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid", "stop");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        BckjBizJob job = bckjBizJobService.get(mapData.get("owid").toString());
        if (job == null) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "查无");
        }
        Integer stop = Integer.parseInt(mapData.get("stop").toString());
        job.setState(stop);
        bckjBizJobService.saveOrUpdate(job);
        return ResponseMessage.sendOK(job);
    }


}