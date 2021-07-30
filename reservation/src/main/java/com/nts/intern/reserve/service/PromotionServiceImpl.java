package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dao.PromotionDao;
import com.nts.intern.reserve.dto.PromotionDto;

@Service
public class PromotionServiceImpl implements PromotionService{
	@Autowired
	private PromotionDao promotionDao;
	
	
	@Override
	public List<PromotionDto> findAll() {
		return promotionDao.findAll();
	}
}
