package com.nts.intern.reserve.interceptor.reserve;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.intern.reserve.dto.reserve.ReservationPrice;

public class ReservationArgumentInterceptor extends HandlerInterceptorAdapter {

	private static final Pattern TEL_PATTERN = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+@\\w+\\.\\w+");
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		if (request.getRequestURI().equals("/api/reservations") == false) {
			return true;
		}
		
		Map<String, String[]> map = request.getParameterMap();

		boolean isValid = true;

		for (Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();

			if (key.equals("form_prices")) {
				isValid &= ticketCount(value[0]);
			}

			if (key.equals("tel")) {
				isValid &= validateTel(value[0]);
			}

			if (key.equals("email")) {
				isValid &= validateEmail(value[0]);
			}
		}

		if (isValid) {
			response.setHeader("validation", "OK");
		} else {
			response.setHeader("validation", "NOT_OK");
		}

		return true;
	}

	public boolean validateTel(String tel) {
		Matcher matcher = TEL_PATTERN.matcher(tel);
		return matcher.find();
	}

	public boolean validateEmail(String email) {
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.find();
	}

	public boolean ticketCount(String tickets) {
		int totalCount = 0;

		try {
			List<ReservationPrice> reservationPrices = objectMapper.readValue(tickets,
				new TypeReference<List<ReservationPrice>>() {});

			for (ReservationPrice price : reservationPrices) {
				totalCount += price.getCount();
			}
		} catch (IOException ioException) {
			System.err.println("전달받은 가격 정보에 파싱할 수 없는 데이터가 있음");
			ioException.printStackTrace();
			return false;
		}

		if (totalCount == 0) {
			return false;
		} else {
			return true;
		}
	}
}
