package com.nts.intern.reserve.service.reviewWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.reviewWrite.ReviewWriteInitDto;
import com.nts.intern.reserve.repository.reviewWrite.ReviewWriteRepository;

@Service
public class ReviewWriteService {
	@Autowired
	private ReviewWriteRepository reviewWriteRepository;
	
	public ReviewWriteInitDto findById(int reservationInfoId) {
		return reviewWriteRepository.findById(reservationInfoId);
	}
}
