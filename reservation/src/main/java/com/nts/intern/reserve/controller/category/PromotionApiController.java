package com.nts.intern.reserve.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.category.PromotionDto;
import com.nts.intern.reserve.service.category.PromotionService;

@RequestMapping("/api/promotions/")
@RestController
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public List<PromotionDto> findAll() {
		return promotionService.findAll();
	}
}
