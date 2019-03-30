package com.pay.merchant.client.config;
import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	private final static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	//数据库连接相关的参数：
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
    private long connectionTimeout = 30000;
	private long idleTimeout = 600000;
	private long maxLifetime = 1765000;
	private int maximumPoolSize = 15;
	@Bean
	@Primary
	public DataSource dataSource1(){
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(userName);
		config.setPassword(password);
		config.setConnectionTimeout(connectionTimeout); 
		config.setIdleTimeout(idleTimeout);
		config.setMaxLifetime(maxLifetime);
		config.setMaximumPoolSize(maximumPoolSize);
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}
	@Bean
    public SqlSessionFactory sqlSessionFactory1() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1());
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageHelper});
        try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager dtm1 = new DataSourceTransactionManager(dataSource1());
		ChainedTransactionManager chainedTransactionManager=new ChainedTransactionManager(dtm1);
		return chainedTransactionManager;
	}
   
}
