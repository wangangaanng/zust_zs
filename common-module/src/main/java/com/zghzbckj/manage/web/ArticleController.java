package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.service.AppBizArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "appBizArticle")
public class ArticleController {
    private static final Logger log = Logger.getLogger(ArticleController.class);

    @Autowired
    private AppBizArticleService appBizArticleService;


    @RequestMapping(value = "/getList")
    @ResponseBody
    public ResponseMessage getListApi(PublicDataVO dataVO) {
        try {
            List<FilterModel> filters = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
            return appBizArticleService.findPageAppBizArticle(filters, dataVO.getPageNo(), dataVO.getPageSize());
        } catch (Exception e) {
            log.error(e + "获取appBizArticle列表失败\r\n" + e.getStackTrace()[0], e);
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
            ResponseMessage data = appBizArticleService.removeOrder(codes);
            return data;
        } catch (Exception e) {
            log.error(e + "删除AppBizArticle列表失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断id是否为
            return appBizArticleService.saveAppBizArticle(mapData);
        } catch (Exception e) {
            log.error(e + "保存AppBizArticle信息失败\r\n" + e.getStackTrace()[0], e);
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
            return ResponseMessage.sendOK(appBizArticleService.get(mapData.get("owid").toString()));
        } catch (Exception e) {

            log.error(e + "初始AppBizArticle\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "getDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getDetail(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

            ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return appBizArticleService.getDetail(mapData);
        } catch (Exception e) {

            log.error(e + "初始AppBizArticle\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "getBeforeList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getBeforeList(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "isDetail", "lmbh2");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return appBizArticleService.getBeforeList(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "saveArticle", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveArticle(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "lmbh", "lmbh2", "sxsj");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return appBizArticleService.saveArticle(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "dealNrAndAtt", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage dealNrAndAtt(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return appBizArticleService.dealNrAndAtt(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "getByEjLmbh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getByEjLmbh(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "lmbh2");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return appBizArticleService.getByEjLmbh(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "genralAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage genralAll(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "all");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return appBizArticleService.genralAll(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }
}
