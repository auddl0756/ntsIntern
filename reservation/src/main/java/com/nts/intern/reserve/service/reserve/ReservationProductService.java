package com.nts.intern.reserve.service.reserve;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.reserve.ReservationPriceDto;
import com.nts.intern.reserve.dto.reserve.ReservationProductDto;
import com.nts.intern.reserve.repository.reserve.ReservationPriceRepository;
import com.nts.intern.reserve.repository.reserve.ReservationProductRepository;

@Service
public class ReservationProductService {
	@Autowired
	private ReservationProductRepository reservationProductRepository;

	@Autowired
	private ReservationPriceRepository reservationPriceRepository;

	private final int randomOffset = 5;

	public ReservationProductDto findById(int displayInfoId) {
		ReservationProductDto result = reservationProductRepository.findById(displayInfoId);
		List<ReservationPriceDto> priceInfos = reservationPriceRepository.findById(displayInfoId);

		priceInfos.stream().forEach(ReservationPriceDto::makeActualPriceTypeName);

		result.setPriceInfos(priceInfos);
		result.setMinimumPrice(reservationPriceRepository.findMinPrice(displayInfoId));
		result.setReservationDate(makeRandomReservationDate());

		return result;
	}

	public String makeRandomReservationDate() {
		LocalDate now = LocalDate.now().plusDays((int)(Math.random() * randomOffset));

		return now.getYear() + "." + now.getMonthValue() + "." + now.getDayOfMonth();
	}
}
