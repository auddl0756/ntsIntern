package com.nts.intern.reserve.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		logger.debug("{} 를 호출했습니다.", handler.toString());

		logger.info("요청 경로 : {}, ip : {} ", request.getRequestURI(), request.getRemoteAddr());

		return true;
	}
}
