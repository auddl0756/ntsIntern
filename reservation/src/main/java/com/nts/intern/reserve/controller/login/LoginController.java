package com.nts.intern.reserve.controller.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "bookinglogin";
	}
	
	@GetMapping("/myreservation")
	public String myReservation(@RequestParam(name="resrv_email") String email,HttpSession session) {
		
		if(session.getAttribute(email)==null) {
			session.setAttribute("email", email);
			System.out.println("session created email = "+email);
		}else {
			System.out.println("already in session attribute email = " +email);
		}
		
		return "myreservation";
	}
}
