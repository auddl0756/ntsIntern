package com.nts.intern.reserve.controller.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.reserve.ReservationLookUpResponseDto;
import com.nts.intern.reserve.service.reserve.ReservationResponseService;

@Controller
public class ReservationResponseController {
	@Autowired
	private ReservationResponseService reservationResponseService;

	@GetMapping("/api/reservations")
	public String getReservationInfo(@RequestParam(name = "resrv_email") String email, Model model) {
		ReservationLookUpResponseDto result = ReservationLookUpResponseDto.builder()
			.reservations(reservationResponseService.findAllReservationsByEmail(email))
			.totalReservationCount(reservationResponseService.findAllReservationsByEmail(email).size())
			.build();

		model.addAttribute("reservationInfo", result);

		return "myreservation";
	}
}
