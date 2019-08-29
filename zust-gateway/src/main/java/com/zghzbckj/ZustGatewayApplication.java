package com.zghzbckj;

import com.zghzbckj.filter.TokenZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZustGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZustGatewayApplication.class, args);
    }

    @Bean
    public TokenZuulFilter tokenFilter() {
        return new TokenZuulFilter();
    }

}
