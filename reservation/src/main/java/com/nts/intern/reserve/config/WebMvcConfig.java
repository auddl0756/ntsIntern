package com.nts.intern.reserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.intern.reserve.interceptor.reserve.ReservationArgumentInterceptor;
//org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
@ComponentScan(basePackages = {"com.nts.intern.reserve.controller"})
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final int ONE_YEAR_SECOND = 365 * 24 * 60 * 60;
	private static final int TEN_MB = 1024 * 1024 * 10;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(ONE_YEAR_SECOND);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(ONE_YEAR_SECOND);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(ONE_YEAR_SECOND);
	}

	// default servlet handler를 사용하게 합니다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ReservationArgumentInterceptor());
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(TEN_MB); 
		return multipartResolver;
	}
}