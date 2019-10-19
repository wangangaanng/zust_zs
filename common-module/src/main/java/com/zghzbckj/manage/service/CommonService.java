package com.zghzbckj.manage.service;

import com.beust.jcommander.internal.Maps;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.utils.HttpUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.util.HttpBackUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@Service("commonService")
public class CommonService {
    private static final Logger log = Logger.getLogger(CommonService.class);

    @Autowired
    BckjBizSybService bckjBizSybService;

    public ResponseMessage getSecondMenu(Map<String, Object> mapData) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("data", JsonUtil.toJson(mapData));
        try {
            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACKEJ_URL_HOST, param, "utf-8", false);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取二级栏目数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendOK(null);
    }

    public ResponseMessage getFirtstMenu(Map<String, Object> mapData) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("data", JsonUtil.toJson(mapData));
        try {
            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACK_URL_HOST, param, "utf-8", false);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取一级栏目数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
    }

    public ResponseMessage getByType(Map<String, Object> mapData) {
        try {
//            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACK_TYPE_URL_HOST, param, "utf-8", false);
            String resStr =  HttpUtil.doPostJson(CommonModuleContant.BACK_TYPE_URL_HOST,  JsonUtil.toJson(mapData), "UTF-8", true);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取字典表数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendOK(null);
    }

    /**
     * 返回字典表 按val2排序
     * @param dataMap
     * @return
     */
    public List<Map<String,Object>> getByTypeSort(Map<String, Object> dataMap) {
        return bckjBizSybService.getByTypeSort(dataMap);
    }
}