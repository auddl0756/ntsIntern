package com.nts.intern.reserve.interceptor;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String params = request.getParameterMap().entrySet().stream()
			.map(entry -> {
				String param = entry.getKey() + " : " + String.join(" , ", entry.getValue());

				return param;
			})
			.collect(Collectors.joining(","));

		logger.info("=== 호출 메서드 : {}", handler.toString() + " ===");
		logger.info("	파라미터  : {{}} ", params);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {

		if (modelAndView == null) {
			logger.info("=== 종료. 리턴한 view는 없습니다. ===");
		} else {
			logger.info("=== 종료. {}를  view로 사용합니다. ===", modelAndView.getViewName());
		}
	}
}
