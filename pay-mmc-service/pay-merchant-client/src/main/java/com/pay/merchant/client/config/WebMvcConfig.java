
package com.pay.merchant.client.config;
import com.pay.merchant.client.intercepter.SystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
   @Autowired
    SystemInterceptor systemInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       this.excludePathPatterns(registry.addInterceptor(systemInterceptor));
    }
    public void excludePathPatterns(InterceptorRegistration registration){
        registration.addPathPatterns("/**");//匹配根目录url
        registration.excludePathPatterns("/");
    }
}

