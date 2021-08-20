package com.nts.intern.reserve.controller.detail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {
	@GetMapping("/review/{displayInfoId}")
	public String review(@PathVariable int displayInfoId) {
		return "review";
	}

	@GetMapping("/detail/{displayInfoId}")
	public String detail(@PathVariable int displayInfoId) {
		return "detail";
	}

	@GetMapping("/reserve/{displayInfoId}")
	public String reserve(@PathVariable int displayInfoId) {
		return "reserve";
	}
}
