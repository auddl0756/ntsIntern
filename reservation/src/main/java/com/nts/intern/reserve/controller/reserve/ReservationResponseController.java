package com.nts.intern.reserve.controller.reserve;

import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;
import com.nts.intern.reserve.dto.reserve.ReservationType;
import com.nts.intern.reserve.service.reserve.ReservationResponseService;

@Controller
public class ReservationResponseController {
	@Autowired
	private ReservationResponseService reservationResponseService;

	private final int SESSION_INTERVAL = 60 * 30;

	@GetMapping("/reservations")
	public String getReservationInfo(@RequestParam(name = "resrv_email") String email, Model model,
		HttpSession session) {

		List<ReservationResponseDto> result = reservationResponseService.findAllReservationsByEmail(email);

		Map<ReservationType, List<ReservationResponseDto>> reservationsByType = result.stream()
			.collect(groupingBy(ReservationResponseDto::getType));

		List<ReservationResponseDto> confirmedReservation = reservationsByType.getOrDefault(ReservationType.CONFIRMED,
			Collections.emptyList());
		List<ReservationResponseDto> usedReservation = reservationsByType.getOrDefault(ReservationType.USED,
			Collections.emptyList());
		List<ReservationResponseDto> canceledReservation = reservationsByType.getOrDefault(ReservationType.CANCELED,
			Collections.emptyList());

		model.addAttribute("confirmedReservation", confirmedReservation);
		model.addAttribute("usedReservation", usedReservation);
		model.addAttribute("canceledReservation", canceledReservation);
		model.addAttribute("totalReservationCount", result.size());
		model.addAttribute("confirmedCount", confirmedReservation.size());
		model.addAttribute("usedCount", usedReservation.size());
		model.addAttribute("canceledCount", canceledReservation.size());

		session.setAttribute("email", email);
		session.setMaxInactiveInterval(SESSION_INTERVAL);

		return "myreservation";
	}
}
