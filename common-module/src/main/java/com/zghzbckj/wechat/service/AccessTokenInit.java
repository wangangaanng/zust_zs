package com.zghzbckj.wechat.service;

import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.base.util.SpringContextHolder;
import com.zghzbckj.manage.entity.SysWxconfig;
import com.zghzbckj.manage.service.SysWxconfigService;

import com.zghzbckj.wechat.WechatConstants;
import com.zghzbckj.wechat.model.AccessToken;
import com.zghzbckj.wechat.model.JSTickets;
import com.zghzbckj.wechat.utils.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
public class AccessTokenInit {
 private static final Logger log = LoggerFactory.getLogger(AccessTokenInit.class);


    public static void  init() {
        SysWxconfigService sysWxconfigService= SpringContextHolder.getBean(SysWxconfigService.class);
        SysWxconfig param=new SysWxconfig();
        List<SysWxconfig>configList= sysWxconfigService.findList(param);
        for (SysWxconfig config : configList) {
            if (config.getState() != 1) {
                continue;
            }
            try {
                //把公众号信息放入缓存
                CacheUtil.setVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + config.getWeCode(), config);
                //服务号
                AccessToken token = null;
                switch (config.getWeType()) {
                    case 0:
                        break;
                    case 1:
                        token = WeixinUtils.getAccessToken(config.getWeAppid(), config.getWeSecrect());
                        break;
                    case 2:
                        token = WeixinUtils.getCompAccessToken(config.getWeAppid(), config.getWeSecrect());
                        break;
                    case 3:
                        token = WeixinUtils.getMiniProgrammAccessToken(config.getWeAppid(), config.getWeSecrect());
                        break;
                    default:
                        token = null;
                        break;
                }
                if (null != token) {
                    CacheUtil.setVal(WechatConstants.WECHAT_REDIS_PREX + config.getWeAppid(), token);
                    CacheUtil.setVal(WechatConstants.WECHAT_REDIS_PREX + config.getWeCode(), token);
                    //获取js安全token
                    try {
                        JSTickets jsTickets = null;
                        switch (config.getWeType()) {
                            case 0:
                                break;
                            case 1:
                                jsTickets = WeixinUtils.getTicket(token.getToken());
                                break;
                            case 2:
                                jsTickets = WeixinUtils.getQyTicket(token.getToken());
                                break;
                            case 3:
                                jsTickets = null;
                                break;
                            default:
                                jsTickets = null;
                                break;
                        }
                        if (null != jsTickets) {
                            CacheUtil.setVal(WechatConstants.WECHAT_JS_REDIS_PREX + config.getWeAppid(), jsTickets);
                            CacheUtil.setVal(WechatConstants.WECHAT_JS_REDIS_PREX + config.getWeCode(), jsTickets);
                        }
                    } catch (Exception e) {
                        log.info(config.getWeName() + " 获取JS令牌失败，" + e.getMessage());
                        continue;
                    }
                }
            } catch (Exception e) {
                log.info(config.getWeName() + " 获取令牌失败，" + e.getMessage());
                continue;
            }
        }
        log.info("====Getting Wechat AccessToken Finished ===== ");
    }




    public static void flashToken(String weCode){
        SysWxconfig config= CacheUtil.getVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + weCode,SysWxconfig.class);
        try {
            //把公众号信息放入缓存
            CacheUtil.setVal(WechatConstants.WECHAT_REDIS_CONFIG_PREX + config.getWeCode(), config);
            //服务号
            AccessToken token = null;
            switch (config.getWeType()) {
                case 0:
                    break;
                case 1:
                    token = WeixinUtils.getAccessToken(config.getWeAppid(), config.getWeSecrect());
                    System.out.println(token.getToken()+"======");
                    break;
                case 2:
                    token = WeixinUtils.getCompAccessToken(config.getWeAppid(), config.getWeSecrect());
                    break;
                case 3:
                    token = WeixinUtils.getMiniProgrammAccessToken(config.getWeAppid(), config.getWeSecrect());
                    break;
                default:
                    token = null;
                    break;
            }
            if (null != token) {
                CacheUtil.setVal(WechatConstants.WECHAT_REDIS_PREX + config.getWeAppid(), token);
                CacheUtil.setVal(WechatConstants.WECHAT_REDIS_PREX + config.getWeCode(), token);
                //获取js安全token
                try {
                    JSTickets jsTickets = null;
                    switch (config.getWeType()) {
                        case 0:
                            break;
                        case 1:
                            jsTickets = WeixinUtils.getTicket(token.getToken());
                            break;
                        case 2:
                            jsTickets = WeixinUtils.getQyTicket(token.getToken());
                            break;
                        case 3:
                            jsTickets = null;
                            break;
                        default:
                            jsTickets = null;
                            break;
                    }
                    if (null != jsTickets) {
                        CacheUtil.setVal(WechatConstants.WECHAT_JS_REDIS_PREX + config.getWeAppid(), jsTickets);
                        CacheUtil.setVal(WechatConstants.WECHAT_JS_REDIS_PREX + config.getWeCode(), jsTickets);
                    }
                } catch (Exception e) {
                    log.info(config.getWeName() + " 获取JS令牌失败，" + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.info(config.getWeName() + " 获取令牌失败，" + e.getMessage());
        }

    }



}
