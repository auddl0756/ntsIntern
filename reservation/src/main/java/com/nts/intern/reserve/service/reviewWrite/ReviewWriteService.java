package com.nts.intern.reserve.service.reviewWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.reviewWrite.ReviewSaveDto;
import com.nts.intern.reserve.dto.reviewWrite.ReviewWriteInitDto;
import com.nts.intern.reserve.repository.reviewWrite.ReviewWriteRepository;

@Service
public class ReviewWriteService {
	@Autowired
	private ReviewWriteRepository reviewWriteRepository;

	public ReviewWriteInitDto findById(int reservationInfoId) {
		return reviewWriteRepository.findById(reservationInfoId);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void saveComment(ReviewSaveDto dto) {
		reviewWriteRepository.save(dto);
	}
}
