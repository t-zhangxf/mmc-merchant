package com.pay.merchant.client.config;

import com.pay.merchant.client.handler.CustomerExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfig {
    @Bean
    public CustomerExceptionHandler getExceptionHandler() {
        return new CustomerExceptionHandler();
    }
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor getCoinsSendExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setKeepAliveSeconds(3000);
        executor.setQueueCapacity(3000);
        executor.setThreadNamePrefix("asyncExecutor");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
