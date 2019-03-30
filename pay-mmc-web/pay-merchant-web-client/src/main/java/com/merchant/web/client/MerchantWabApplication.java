package com.merchant.web.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = { "com.merchant.web.*" })
@EnableFeignClients(basePackages = "com.merchant.web.integration")
public class MerchantWabApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantWabApplication.class, args);
    }

}
