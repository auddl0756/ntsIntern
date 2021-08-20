package com.nts.intern.reserve.controller.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.reserve.ReservationProductDto;
import com.nts.intern.reserve.service.reserve.ReservationProductService;

@RestController
public class ProductReservationApiController {
	@Autowired
	private ReservationProductService reservationProductService;

	@GetMapping("/api/reservation/product/{displayInfoId}")
	public ReservationProductDto getProductData(@PathVariable int displayInfoId) {
		return reservationProductService.findById(displayInfoId);
	}
}
