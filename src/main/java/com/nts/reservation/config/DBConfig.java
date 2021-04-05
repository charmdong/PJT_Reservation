package com.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 데이터베이스 설정
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 17.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBConfig {
	/*
	 * @Value("${spring.datasource.driver-class-name}") private String
	 * driverClassName;
	 * 
	 * @Value("${spring.datasource.url}") private String url;
	 * 
	 * @Value("${spring.datasource.username}") private String username;
	 * 
	 * @Value("${spring.datasource.password}") private String password;
	 */
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
	private String username = "connectuser";
	private String password = "connect123!@#";
	/**
	 * @return dataSource
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	/**
	 * @return DataSourceTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
