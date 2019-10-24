package com.zghzbckj.manage.service;

import com.beust.jcommander.internal.Maps;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.utils.HttpUtil;
import com.zghzbckj.util.HttpBackUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;

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

    @Transactional(readOnly = false)
    public void sendCode(Map<String, Object> mapData) throws CustomerException {
        BckjBizYhxx yhxx=bckjBizYhxxService.getBySwZh(mapData,"unionid");
        if(null==yhxx){
            throw new CustomerException("不存在基础信息，请重新进入小程序");
        }
        if(yhxx.getState()==1){
            throw new CustomerException("此用户已经绑定");
        }
        yhxx.setSwZh(MapUtils.getString(mapData,"swZh"));
        yhxx.setYzm(getRandom());
        yhxx.setFssj(new Date());
        bckjBizYhxxService.saveOrUpdate(yhxx);
    }

    private String getRandom(){
        Random rd = new Random();
        String tmp = "";
        for (int i = 0; i < 6; i++) {
            tmp += rd.nextInt(10);
        }
        return tmp;
    }

    @Transactional(readOnly = false)
    public void sendCodeForget(Map<String, Object> mapData) throws CustomerException{
        BckjBizYhxx yhxx=bckjBizYhxxService.getBySwZh(mapData,"swZh");
        if (null != yhxx) {
            throw new CustomerException("不存在此用户");
        }
        if(yhxx.getState()==0){
            throw new CustomerException("此手机号未注册");
        }
        yhxx.setYzm(getRandom());
        yhxx.setFssj(new Date());
        bckjBizYhxxService.saveOrUpdate(yhxx);
    }
}