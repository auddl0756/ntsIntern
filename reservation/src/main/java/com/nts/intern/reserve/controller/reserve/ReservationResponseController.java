package com.nts.intern.reserve.controller.reserve;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;
import com.nts.intern.reserve.service.reserve.ReservationResponseService;
import com.nts.intern.reserve.util.filter.ReservationFilter;
import com.nts.intern.reserve.util.filter.ReservationTimePredicate;

@Controller
public class ReservationResponseController {
	@Autowired
	private ReservationResponseService reservationResponseService;

	private final int SESSION_INTERVAL = 60 * 30;

	private static ReservationTimePredicate<ReservationResponseDto> reservationTimePredicate = new ReservationTimePredicate<>();

	@GetMapping("/reservations")
	public String getReservationInfo(@RequestParam(name = "resrv_email") String email, Model model,
		HttpSession session) {

		List<ReservationResponseDto> result = reservationResponseService.findAllReservationsByEmail(email);
		
		List<ReservationResponseDto> canceledReservation = ReservationFilter.filter(result,
			ReservationResponseDto::isCancelYn);
		List<ReservationResponseDto> notCanceledReservation = ReservationFilter.filter(result, dto -> {
			return !dto.isCancelYn();
		});

		List<ReservationResponseDto> confirmedReservation = ReservationFilter.filter(notCanceledReservation,
			reservationTimePredicate);
		List<ReservationResponseDto> usedReservation = ReservationFilter.filter(notCanceledReservation,
			reservationTimePredicate.negate());

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
