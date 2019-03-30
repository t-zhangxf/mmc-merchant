package com.pay.merchant.client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = { "com.pay.merchant.*" })
@EnableFeignClients(basePackages = "com.pay.merchant.integration")
public class MerchantServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantServerApplication.class, args);
    }
}

