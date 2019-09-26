package com.zghzbckj.web.schedule;

import com.ourway.base.utils.TextUtils;
import com.zghzbckj.web.constant.Constant;
import com.zghzbckj.web.controll.ApiConstants;
import com.zghzbckj.web.utils.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by jackson on 17-3-8.
 */
@Component
public class SystemJobSerImpl {
    private static final Logger logger = Logger.getLogger(SystemJobSerImpl.class);
    


    @Scheduled(fixedDelay = 3600*2*1000)
    public void initSystem() {
        logger.info("系统参数初始中....");
        try {
            if (TextUtils.isEmpty(Constant.APP_KEY) || TextUtils.isEmpty(Constant.URL_HOST)) {
                PropertiesUtil propertiesUtil = new PropertiesUtil();
                Properties p = propertiesUtil.loadProperties("config.properties");
                Constant.APP_KEY = PropertiesUtil.readProperty(p, Constant.CLIENT_KEY);
                Constant.URL_HOST = PropertiesUtil.readProperty(p, Constant.HOST_KEY);
                Constant.APP_SECTRECT = PropertiesUtil.readProperty(p, Constant.SECRECT_KEY);
                ApiConstants.imagePath= PropertiesUtil.readProperty(p, Constant.IMAGEPATH);
                ApiConstants.localUrl=PropertiesUtil.readProperty(p, Constant.LOCALURL);
                ApiConstants.uploadUrl=PropertiesUtil.readProperty(p, Constant.UPLOADURL);

            }
        } catch (Exception e) {
            logger.error("初始化失败{}",e);
        }
    }

}
