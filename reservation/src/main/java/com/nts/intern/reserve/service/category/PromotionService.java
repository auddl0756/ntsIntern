package com.nts.intern.reserve.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.category.PromotionDto;
import com.nts.intern.reserve.repository.category.PromotionRepository;

@Service
public class PromotionService {
	@Autowired
	private PromotionRepository promotionRepository;

	@Transactional(readOnly = true)
	public List<PromotionDto> findAll() {
		return promotionRepository.findAll();
	}
}
