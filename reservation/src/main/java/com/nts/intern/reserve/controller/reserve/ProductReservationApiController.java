package com.nts.intern.reserve.controller.reserve;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.intern.reserve.dto.reserve.ProductDto;
import com.nts.intern.reserve.dto.reserve.ReservationParam;
import com.nts.intern.reserve.dto.reserve.ReservationPrice;
import com.nts.intern.reserve.service.reserve.ProductService;
import com.nts.intern.reserve.service.reserve.ReservationProductService;

@RestController
public class ProductReservationApiController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ReservationProductService reservationProductService;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/api/reservation/product/{displayInfoId}")
	public ProductDto getProductData(@PathVariable int displayInfoId) {
		return productService.findById(displayInfoId);
	}

	@PostMapping("/api/reservations")
	public void makeReservation(
		@RequestParam("name") String reservationName,
		@RequestParam("email") String reservationEmail,
		@RequestParam("tel") String reservationTel,
		@RequestParam("form_product_id") int productId,
		@RequestParam("form_display_info_id") int displayInfoId,
		@RequestParam("form_date") String reservationDate,
		@RequestParam("form_prices") String priceStrings,
		HttpServletResponse response) {

		try {
			List<ReservationPrice> reservationPrices = objectMapper.readValue(priceStrings,
				new TypeReference<List<ReservationPrice>>() {});

			ReservationParam reservationParam = ReservationParam.builder()
				.reservationName(reservationName)
				.reservationEmail(reservationEmail)
				.reservationTel(reservationTel)
				.productId(productId)
				.displayInfoId(displayInfoId)
				.reservationDate(reservationDate)
				.reservationPrices(reservationPrices)
				.build();

			reservationProductService.save(reservationParam);
			
			response.sendRedirect("/");

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

	}
}
