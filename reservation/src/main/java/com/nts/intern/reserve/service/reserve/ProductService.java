package com.nts.intern.reserve.service.reserve;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private final int randomOffset = 5;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	public ProductDto findById(int displayInfoId) {
		ProductDto result = productRepository.findById(displayInfoId);
		List<PriceDto> priceInfos = priceRepository.findById(displayInfoId);

		priceInfos.forEach(PriceDto::makeActualPriceTypeName);

		result.setPriceInfos(priceInfos);
		result.setReservationDate(makeRandomReservationDate());

		return result;
	}

	public String makeRandomReservationDate() {
		LocalDate reservationDate = LocalDate.now().plusDays((int)(Math.random() * randomOffset));

		return reservationDate.format(FORMATTER);
	}
}
