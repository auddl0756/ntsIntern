package com.nts.intern.reserve.service.reserve;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.reserve.PriceDto;
import com.nts.intern.reserve.dto.reserve.ProductDto;
import com.nts.intern.reserve.repository.reserve.PriceRepository;
import com.nts.intern.reserve.repository.reserve.ProductRepository;

@Service("reservationPageProductService")
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PriceRepository priceRepository;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	private final int randomOffset = 5;

	@Transactional(readOnly = true)
	public ProductDto findById(int displayInfoId) {
		ProductDto result = productRepository.findById(displayInfoId);
		List<PriceDto> priceInfos = priceRepository.findById(displayInfoId);

		priceInfos.forEach(PriceDto::makeActualPriceTypeName);

		result.setPriceInfos(priceInfos);
		result.setReservationDate(makeRandomReservationDate());

		return result;
	}

	String makeRandomReservationDate() {
		LocalDateTime reservationDate = LocalDateTime.now().plusDays((int)(Math.random() * randomOffset));
		return reservationDate.format(FORMATTER);
	}

	public int cancelReservation(int reservationInfoId) {
		return productRepository.updateCancelFlag(reservationInfoId);
	}
}
