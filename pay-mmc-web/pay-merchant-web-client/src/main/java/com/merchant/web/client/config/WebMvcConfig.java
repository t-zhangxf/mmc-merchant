
package com.merchant.web.client.config;
import com.merchant.web.client.intercepter.SystemInterceptor;
import com.merchant.web.client.intercepter.UserInterceptor;
import com.merchant.web.common.enums.EnvEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Value("${spring.profiles.active}")
    private String env;
    @Bean
    public SystemInterceptor systemInterceptor(){//swagger api 拦截器
        return  new SystemInterceptor();
    }
    @Bean
    public UserInterceptor userInterceptor(){//用户拦截器
        return  new UserInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       this.excludePathPatterns(registry.addInterceptor(systemInterceptor()));
       if(EnvEnums.ENV_PROD.getCode().equals(env)){
           this.excludePathPatterns(registry.addInterceptor(userInterceptor()));
       }
    }
    public void excludePathPatterns(InterceptorRegistration registration){
        registration.addPathPatterns("/**");//匹配根目录url
        registration.excludePathPatterns("/")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/error")
                .excludePathPatterns("/user/loginOut")
                .excludePathPatterns("/user/reset/pwd/first")
                .excludePathPatterns("/user/reset/pwd/reSet")
                .excludePathPatterns("/mail/getUserEmailByToken")
                .excludePathPatterns("/mail/send/pwd");
    }
}

