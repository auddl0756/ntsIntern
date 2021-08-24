package com.nts.intern.reserve.controller.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.nts.intern.reserve.dto.reserve.ProductDto;
import com.nts.intern.reserve.service.reserve.ProductService;

@RestController
public class ProductReservationApiController {
	@Autowired
	private ProductService productService;

	@GetMapping("/api/reservation/product/{displayInfoId}")
	public ProductDto getProductData(@PathVariable int displayInfoId) {
		return productService.findById(displayInfoId);
	}
}
