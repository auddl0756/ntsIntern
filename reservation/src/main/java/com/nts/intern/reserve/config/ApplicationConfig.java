package com.nts.intern.reserve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.nts.intern.reserve.repository", "com.nts.intern.reserve.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
