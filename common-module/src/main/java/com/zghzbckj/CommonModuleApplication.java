package com.zghzbckj;


import com.zghzbckj.base.config.EnableUserInfoTransmitter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableUserInfoTransmitter
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class CommonModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonModuleApplication.class, args);
    }

}
