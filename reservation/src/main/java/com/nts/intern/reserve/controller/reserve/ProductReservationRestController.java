package com.nts.intern.reserve.controller.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nts.intern.reserve.dto.reserve.ProductDto;
import com.nts.intern.reserve.service.reserve.ProductService;

@RestController
public class ProductReservationRestController {
	@Autowired
	private ProductService productService;

	@GetMapping("/api/reservation/product/{displayInfoId}")
	public ProductDto getProductData(@PathVariable int displayInfoId) {
		return productService.findById(displayInfoId);
	}

	@PutMapping("/api/reservations/{reservationInfoId}")
	public HttpStatus cancelReservation(@PathVariable int reservationInfoId) {
		int result = productService.cancelReservation(reservationInfoId);
		if (result == 1) {
			return HttpStatus.OK;
		} else if (result == 0) {
			return HttpStatus.NOT_FOUND;
		} else {
			return HttpStatus.FORBIDDEN;
		}
	}
}
