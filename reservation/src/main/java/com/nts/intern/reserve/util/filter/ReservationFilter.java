package com.nts.intern.reserve.util.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReservationFilter {
	public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		List<T> result = new ArrayList<>();

		for (T dto : list) {
			if (predicate.test(dto)) {
				result.add(dto);
			}
		}
		return result;
	}
}
