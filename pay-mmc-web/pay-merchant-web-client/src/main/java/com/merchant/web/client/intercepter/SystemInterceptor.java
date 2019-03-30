package com.merchant.web.client.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SystemInterceptor implements HandlerInterceptor{
	@Value("${spring.profiles.active}")
	private String envConfig;
	@Value("${var.str.swagger}")
	private String swaggerStr;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		try {
			 String uri=request.getRequestURI();
             if(!"prod".equals(envConfig)){
                return true;
			 }else{//正式环境
                String arrayStr[]=swaggerStr.split(",");
                for(String str:arrayStr ){
					if(uri.contains(str)){
						return false;
					}
				}
			 }
			return true;
		}catch (Exception e){
				throw  e;
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
     
}
