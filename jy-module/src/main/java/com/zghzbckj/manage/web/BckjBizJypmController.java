/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.entity.BckjBizJypm;
import com.zghzbckj.manage.service.BckjBizJypmService;
import com.zghzbckj.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;


/**
 * ccController
 *
 * @author cc
 * @version 2019-09-09
 */
@Controller
@RequestMapping(value = "bckjBizJypm")
public class BckjBizJypmController extends BaseController {
    @Autowired
    private BckjBizJypmService bckjBizJypmService;

    /**
     * <p>功能描述:就业排行榜显示 jypmList</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/9/10 14:13</li>
     * </ul>
     */
    @PostMapping(value = "jypmList")
    @ResponseBody
    public ResponseMessage jypmList(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("orderBy", "szxy");
            PageInfo<Map<String, Object>> pageInfo = bckjBizJypmService.rankPage(dataMap, dataVO.getPageNo(), dataVO.getPageSize());
            return ResponseMessage.sendOK(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    /**
     *<p>功能描述:从excel导入就业排行榜</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/20 16:43</li>
     *</ul>
     */
    @PostMapping(value = "importRankFromExcel")
    @ResponseBody
    public ResponseMessage importRankFromExcel(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "excelUrl");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            ArrayList<ArrayList<String>> list = ExcelUtils.xlsx_reader(dataMap.get("excelUrl").toString());
            if (null == list) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "Excel表为空");
            }
            List<BckjBizJypm> jypmList = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                ArrayList<String> cellList = list.get(i);
                String szxy = cellList.get(0);    //所在学院
                String pmzy = cellList.get(1);    //排名专业
                String pmbj = cellList.get(2);    //排名班级
                String pmnf = cellList.get(3);    //排名年份
                String pmyf = cellList.get(4);    //排名月份
                String pmbyrs = cellList.get(5);  //排名毕业人数
                String pmyprs = cellList.get(6);  //排名应聘人数
                String pmqyrs = cellList.get(7);  //排名签约人数
                String pmqyl = cellList.get(8);   //排名签约率
                String pmjyl = cellList.get(9);   //排名就业率

                BckjBizJypm jypm = new BckjBizJypm();
                //根据专业名称查询
                BckjBizJypm major = bckjBizJypmService.getByMajor(pmzy);
                if (!TextUtils.isEmpty(major)) {
                    jypm.setOwid(major.getOwid());
                }
                jypm.easySet("szxy", szxy, "pmzy", pmzy, "pmbj", pmbj, "pmnf", pmnf, "pmyf", pmyf);
                jypm.setPmbyrs(Integer.parseInt(pmbyrs));
                jypm.setPmyprs(Integer.parseInt(pmyprs));
                jypm.setPmqyrs(Integer.parseInt(pmqyrs));
                jypm.setPmqyl(new BigDecimal(pmqyl));
                jypm.setPmjyl(new BigDecimal(pmjyl));
                jypmList.add(jypm);
            }
            bckjBizJypmService.saveOrUpdateAll(jypmList);
            return ResponseMessage.sendOK("导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJypmService.findPageBckjBizJypm(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取bckjBizJypm列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = bckjBizJypmService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除BckjBizJypm列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return bckjBizJypmService.saveBckjBizJypm(mapData);
        } catch (Exception  e) {
            log.error(e + "保存BckjBizJypm信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(bckjBizJypmService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始BckjBizJypm\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


}