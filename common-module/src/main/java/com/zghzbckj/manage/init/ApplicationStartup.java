package com.zghzbckj.manage.init;

import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.config.Global;

import com.zghzbckj.wechat.service.AccessTokenInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by D.chen.g on 2018/8/14.
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        logger.info("系统参数初始化中....");
        try {
           CommonConstants.URL_HOST= Global.getConfig("url_host");
            AccessTokenInit.init();
        } catch (Exception e) {

            logger.error("初始化失败{}", e);
        }
    }

}
