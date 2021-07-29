package com.nts.intern.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	@GetMapping("/main")
	public String mainPage() {
		return "mainpage";
	}
}
