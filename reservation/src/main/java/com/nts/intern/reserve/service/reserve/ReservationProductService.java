package com.nts.intern.reserve.service.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.reserve.ReservationProductDto;
import com.nts.intern.reserve.repository.reserve.ReservationProductRepository;

@Service
public class ReservationProductService {
	@Autowired
	private ReservationProductRepository reservationProductRepository;

	public List<ReservationProductDto> findById(int displayInfoId) {
		return reservationProductRepository.findById(displayInfoId);
	}
}
