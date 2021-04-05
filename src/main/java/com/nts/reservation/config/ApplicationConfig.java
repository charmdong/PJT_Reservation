package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 어플리케이션 설정
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 17.
 */
@Configuration
@ComponentScan(basePackages = { "com.nts.reservation.repository", "com.nts.reservation.service" })
@Import({ DBConfig.class })
public class ApplicationConfig {

}
