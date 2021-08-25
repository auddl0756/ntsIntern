package com.nts.intern.reserve.service.reserve;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;
import com.nts.intern.reserve.repository.reserve.ReservationResponseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationResponseService {
	private final ReservationResponseRepository reservationResponseRepository;

	@Transactional(readOnly = true)
	public List<ReservationResponseDto> findAllReservationsByEmail(String email) {
		return reservationResponseRepository.findAllReservationsByEmail(email);
	}
}
