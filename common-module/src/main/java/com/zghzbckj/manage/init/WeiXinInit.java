package com.zghzbckj.manage.init;


import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.wechat.service.AccessTokenInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Order(1)
@Component
public class WeiXinInit implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
    @Override
    public void run(String... args) throws Exception {
        logger.info("系统参数初始化中....");
        try {
            CommonConstants.URL_HOST= Global.getConfig("url_host");
            AccessTokenInit.init();
        } catch (Exception e) {
            logger.error("初始化失败{}", e);
        }
    }
}
