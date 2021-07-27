package com.nts.intern.reserve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.nts.intern.reserve.dao","com.nts.intern.reserve.service"})
public class ApplicationConfig {
	
}
