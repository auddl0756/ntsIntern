package com.nts.intern.reserve.service.reserve;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;
import com.nts.intern.reserve.repository.reserve.DisplayInfoRepository;
import com.nts.intern.reserve.repository.reserve.ReservationResponseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationResponseService {
	private final DisplayInfoRepository displayInfoRepository;
	private final ReservationResponseRepository reservationResponseRepository;

	public List<ReservationResponseDto> findAllReservationsByEmail(String email) {
		List<ReservationResponseDto> result = reservationResponseRepository.findAllReservationsByEmail(email);

		result.forEach(dto -> {
			dto.setDisplayInfo(displayInfoRepository.findById(dto.getDisplayInfoId()));
			dto.setTotalPrice(reservationResponseRepository.getTotalPrice(dto.getReservationInfoId()));
		});

		return result;
	}
}
