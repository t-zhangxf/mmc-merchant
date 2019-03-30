package com.pay.merchant.client.config;
import com.pay.merchant.repository.MyDbRepository;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MyBatisMapperScannerConfig {
	/**
	 * - 设置SqlSessionFactory；
	 * - 设置dao所在的package路径；
	 * - 关联注解在dao类上的Annotation名字；
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer1() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
	    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory1");
		mapperScannerConfigurer.setBasePackage("com.pay.merchant.*");
		mapperScannerConfigurer.setAnnotationClass(MyDbRepository.class);
		return mapperScannerConfigurer;
	}
}
