package com.nts.intern.reserve.controller.reviewWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.service.reviewWrite.ProductDescriptionService;

@RestController
public class ReviewWriteApiController {
	@Autowired
	private ProductDescriptionService productDescriptionService;
	
	@GetMapping("/api/productDescription/{reservationInfoId}")
	public String getProductDescription(@PathVariable int reservationInfoId) {
		return productDescriptionService.findById(reservationInfoId);
	}
}
