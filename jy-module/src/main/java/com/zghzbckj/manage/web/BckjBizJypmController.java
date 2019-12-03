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
import com.zghzbckj.manage.entity.BckjBizJypm;
import com.zghzbckj.manage.service.BckjBizJypmService;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.IpAdrressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public ResponseMessage jypmList(HttpServletRequest request, PublicDataVO dataVO) {
        try {
            String ip = IpAdrressUtil.getIpAdrress(request);
            System.out.println(ip);
            if (innerIp(ip)) {
                Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
                List<Map<String, Object>> rankList = bckjBizJypmService.listRank(dataMap);
                return ResponseMessage.sendOK(rankList);
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "仅限内网IP用户查看");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统错误");
        }
    }

    /**
     * <p>功能描述:最近几年 getRecentYears</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/9/23 17:22</li>
     * </ul>
     */
    @PostMapping(value = "getRecentYears")
    @ResponseBody
    public ResponseMessage getRecentYears(PublicDataVO dataVO) {
        Calendar calendar = Calendar.getInstance();
        String[] recentYears = new String[3];
        int thisYear = calendar.get(Calendar.YEAR);
        for (int i = 0; i < 3; i++) {
            recentYears[i] = String.valueOf(thisYear);
            thisYear = thisYear - 1;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("recentYears", recentYears);
        return ResponseMessage.sendOK(result);
    }

    /**
     * <p>功能描述:从excel导入就业排行榜</p >
     * <ul>
     * <li>@param [dataVO]</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/9/20 16:43</li>
     * </ul>
     */
    @PostMapping(value = "importRankFromExcel")
    @ResponseBody
    public ResponseMessage importRankFromExcel(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            ArrayList<ArrayList<String>> list = ExcelUtils.xlsx_reader(dataMap.get("path").toString());
            if (null == list) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "Excel表为空");
            }
            List<BckjBizJypm> jypmList = new ArrayList<>();

            for (int i = 2; i < list.size(); i++) {
                ArrayList<String> cellList = list.get(i);
                //所在学院
                String szxy = cellList.get(0);
                //排名专业
                String pmzy = cellList.get(1);
                //排名年份
                String pmnf = cellList.get(2);
//                String pmyf = cellList.get(3);    //排名月份
                //排名毕业人数
                String pmbyrs = cellList.get(3);
                //就业人数
                String pmqyrs = cellList.get(4);
                //排名就业率
                String pmjyl = cellList.get(5);

                BckjBizJypm jypm = new BckjBizJypm();
                //根据不同年份学院专业名称查询
                BckjBizJypm major = bckjBizJypmService.getByCollegeMajor(szxy, pmzy);
                if (!TextUtils.isEmpty(major)) {
                    jypm.setOwid(major.getOwid());
                }
                jypm.easySet("szxy", szxy, "pmzy", pmzy, "pmnf", pmnf);
                jypm.setPmbyrs(Integer.parseInt(pmbyrs));
                jypm.setPmqyrs(Integer.parseInt(pmqyrs));
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

    /**
     * <p>方法:判断是否内网IP innerIp TODO </p>
     * <ul>
     * <li> @param ip TODO</li>
     * <li>@return boolean  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/26 16:18  </li>
     * </ul>
     */
    private boolean innerIp(String ip) {
        Pattern reg = Pattern.compile("^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$");
        Matcher match = reg.matcher(ip);
        return match.find();
    }

    /**
     *<p>功能描述:清空所有数据 deleteAll</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/10/30 11:39</li>
     *</ul>
     */
    @PostMapping(value = "deleteAll")
    @ResponseBody
    public ResponseMessage deleteAll(PublicDataVO dataVO) {
        try {
            bckjBizJypmService.deleteAll();
            return ResponseMessage.sendOK("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:导出合并单元格的excel</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/8 10:22</li>
     *</ul>
     */
    @PostMapping(value = "exportRankExcel")
    @ResponseBody
    public ResponseMessage exportRankExcel(PublicDataVO dataVO) {
        try {
            String fileName = bckjBizJypmService.exportRankExcel();
            return ResponseMessage.sendOK(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     *<p>功能描述:保存历年排行榜数据 saveLn</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/26 17:13</li>
     *</ul>
     */
    @PostMapping(value = "saveLn")
    @ResponseBody
    public ResponseMessage saveLn(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            List<BckjBizJypm> jypmList = bckjBizJypmService.findListByParams(dataMap, null);
            bckjBizJypmService.deleteThisYear(MapUtils.getString(dataMap, "pmnf"));
            for (BckjBizJypm jypm : jypmList) {
                jypm.setOwid("");
                jypm.setPmnf(MapUtils.getString(dataMap, "pmnf"));
                jypm.setState(1);
                bckjBizJypmService.save(jypm);
            }
            return ResponseMessage.sendOK("ok");
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

    /**
     *<p>功能描述:历年排行榜列表 getListByNf</p >
     *<ul>
     *<li>@param [dataVO]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/26 17:14</li>
     *</ul>
     */
    @PostMapping(value = "getListByNf")
    @ResponseBody
    public ResponseMessage getListByNf(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return bckjBizJypmService.findPageBckjBizJypmNf(filters, dataVO.getPageNo(), dataVO.getPageSize());
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
        } catch (Exception e) {
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

    /**
     * <p>接口 recordJobInfo.java : <p>
     * 导入
     * <pre>
     * @author cc
     * @date 2019/10/30 9:22
     * </pre>
     */
    @RequestMapping(value = "recordJobInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordJobInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizJypmService.recordJobInfo(dataMap.get("path").toString());
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "recordBmInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage recordBmInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "path", "yqRefOwid", "jobRefOwid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return bckjBizJypmService.recordBmInfo(dataMap);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }


}