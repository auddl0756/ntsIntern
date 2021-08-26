package com.nts.intern.reserve.service.reviewWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.repository.reviewWrite.ProductDescriptionRepository;

@Service
public class ProductDescriptionService {
	@Autowired
	private ProductDescriptionRepository productDescriptionRepository;
	
	public String findById(int reservationInfoId) {
		return productDescriptionRepository.findById(reservationInfoId);
	}
}
