package com.nts.intern.reserve.controller.reserve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.intern.reserve.dto.reserve.ReservationParam;
import com.nts.intern.reserve.dto.reserve.ReservationPrice;
import com.nts.intern.reserve.service.reserve.ReservationProductService;

@Controller
public class ProductReservationController {
	@Autowired
	private ReservationProductService reservationProductService;

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	
	@PostMapping("/api/reservations")
	public String makeReservation(
		@RequestParam("name") String reservationName,
		@RequestParam("email") String reservationEmail,
		@RequestParam("tel") String reservationTel,
		@RequestParam("form_product_id") int productId,
		@RequestParam("form_display_info_id") int displayInfoId,
		@RequestParam("form_date") String reservationDate,
		@RequestParam("form_prices") String priceStrings) {

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
				.createDate(FORMATTER.format(LocalDateTime.now()))
				.modifyDate(FORMATTER.format(LocalDateTime.now()))
				.build();

			reservationProductService.save(reservationParam);

		}catch(NullPointerException nullException) {
			System.err.println("필수 파라미터 중에 null인 것이 있음.");
			nullException.printStackTrace();
		}catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "redirect:/";
	}
}
