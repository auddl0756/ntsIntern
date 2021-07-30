package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.PromotionDto;
import com.nts.intern.reserve.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public List<PromotionDto> findAll() {
		return promotionRepository.findAll();
	}
}
