package com.zghzbckj.manage.web;

import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.util.CacheUtil;


import com.zghzbckj.manage.entity.SysWxconfig;
import com.zghzbckj.manage.utils.SmallAppUtil;
import com.zghzbckj.util.TextUtils;
import com.zghzbckj.wechat.model.WxXcxUserModel;
import com.zghzbckj.wechat.utils.AesCbcUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;



public class WxController {

    private static final Logger log = LoggerFactory.getLogger(WxController.class);


    public static ResponseMessage getOpenId (Map<String,Object> dataMap) {
        String code= dataMap.get("code").toString();
        String wxid = dataMap.get("wxid").toString();
        String iv = dataMap.get("iv").toString();
        String encryptedData = dataMap.get("encryptedData").toString();
        log.info(code + "================================================");
        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(wxid)) {
            return null;
        }
        if (TextUtils.isEmpty(iv) || TextUtils.isEmpty(encryptedData)) {
            return null;
        }
        SysWxconfig wxconfig = CacheUtil.getVal("sys.wechat.config." + wxid, SysWxconfig.class);
        Map result = SmallAppUtil.getOpenId(wxconfig.getWeAppid(), wxconfig.getWeSecrect(), code);
        if (null != result) {
            String session_key = result.get("session_key").toString();
            log.info(" session_key---" + session_key);
            try {
                String results = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
                log.info(" results---" + results);
                WxXcxUserModel userModel = SmallAppUtil.getWxXcxUser(results);

                return ResponseMessage.sendOK(userModel);
            } catch (Exception e) {
                log.error("获取unionid解密失败{}", e);
                return ResponseMessage.sendError(ResponseMessage.FAIL, "系统故障");
            }
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "空");
        }
    }


}
