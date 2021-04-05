package com.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.reservation.interceptor.LogInterceptor;
import com.nts.reservation.interceptor.LoginInterceptor;

/**
 * MVC 설정
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.nts.reservation.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	/**
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("file:C:/tmp/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/img_map/**").addResourceLocations("file:C:/tmp/img_map/").setCachePeriod(31556926);
		// registry.addResourceHandler("/commentImages/**").addResourceLocations("file:c:/tmp/commentImages/").setCachePeriod(31556926);
		registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}

	/**
	 * @param registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
	}

	/**
	 * @return resolver
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/bookinglogin");
		registry.addInterceptor(new LogInterceptor()).excludePathPatterns("/api/**");
	}

	/**
	 * @return multipartResolver
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
		return multipartResolver;
	}
}
