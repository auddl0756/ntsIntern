package com.nts.intern.reserve.util.filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;

public class ReservationTimePredicate<T> implements Predicate<T> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

	@Override
	public boolean test(Object object) {
		ReservationResponseDto dto = (ReservationResponseDto)object;

		LocalDateTime nowTime = LocalDateTime.now();
		LocalDateTime reservationDate = LocalDateTime.parse(dto.getReservationDate(), FORMATTER);

		if (nowTime.compareTo(reservationDate) <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
