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
	public int save(ReservationParam reservationParam) {
		int newestId = reservationProductRepository.saveReservationInfo(reservationParam);

		//오직 reservation_info_price 테이블의 영향을 받은 행의 갯수  
		int affectRowCount = 0;

		for (ReservationPrice price : reservationParam.getReservationPrices()) {
			price.setReservationInfoId(newestId);
			affectRowCount += reservationProductRepository.saveReservationInfoPrice(price);
		}

		return affectRowCount;
	}
}
