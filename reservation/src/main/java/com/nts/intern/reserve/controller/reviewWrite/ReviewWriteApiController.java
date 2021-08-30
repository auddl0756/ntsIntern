package com.nts.intern.reserve.controller.reviewWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.reviewWrite.ReviewWriteInitDto;
import com.nts.intern.reserve.service.reviewWrite.ReviewWriteService;

@RestController
public class ReviewWriteApiController {
	@Autowired
	private ReviewWriteService reviewWriteService;

	@GetMapping("/api/productDescription/{reservationInfoId}")
	public ReviewWriteInitDto getProductDescription(@PathVariable int reservationInfoId) {
		return reviewWriteService.findById(reservationInfoId);
	}
}
