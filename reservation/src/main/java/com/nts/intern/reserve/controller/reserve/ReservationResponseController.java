package com.nts.intern.reserve.controller.reserve;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;
import com.nts.intern.reserve.service.reserve.ReservationResponseService;

@Controller
public class ReservationResponseController {
	@Autowired
	private ReservationResponseService reservationResponseService;

	@GetMapping("/api/reservations")
	public String getReservationInfo(@RequestParam(name = "resrv_email") String email, Model model,
		HttpSession session) {
		
		List<ReservationResponseDto> result = reservationResponseService.findAllReservationsByEmail(email);
		
		model.addAttribute("reservationInfo", result);
		model.addAttribute("reservationCount",result.size());
		
		session.setAttribute("email", email);

		return "myreservation";
	}
}
