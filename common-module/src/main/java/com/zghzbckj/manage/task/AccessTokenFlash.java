package com.zghzbckj.manage.task;

import com.zghzbckj.wechat.service.AccessTokenInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenFlash {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void falshToken() {
        try {
//            AccessTokenInit.flashToken(CommonConstant.WX_CODE);
            System.out.println("定时任务执行");
            AccessTokenInit.init();
        } catch (Exception throwable) {
            log.error(throwable.toString());
        }
    }

}
