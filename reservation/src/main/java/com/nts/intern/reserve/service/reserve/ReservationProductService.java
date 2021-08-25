package com.nts.intern.reserve.service.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.reserve.ReservationParam;
import com.nts.intern.reserve.dto.reserve.ReservationPrice;
import com.nts.intern.reserve.repository.reserve.ReservationProductRepository;

@Service
public class ReservationProductService {
	@Autowired
	private ReservationProductRepository reservationProductRepository;

	@Transactional
	public void save(ReservationParam reservationParam) {
		int newestId = reservationProductRepository.saveReservationInfo(reservationParam);

		for (ReservationPrice price : reservationParam.getReservationPrices()) {
			price.setReservationInfoId(newestId);
			reservationProductRepository.saveReservationInfoPrice(price);
		}

		return ;
	}
}
