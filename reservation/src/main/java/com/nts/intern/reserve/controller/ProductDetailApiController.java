package com.nts.intern.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.ProductDetailResponseDto;
import com.nts.intern.reserve.service.ProductDetailService;

@RestController
public class ProductDetailApiController {
	@Autowired
	private ProductDetailService productDetailService;

	@GetMapping(value = "/api/products/{displayInfoId}")
	public ProductDetailResponseDto findDetailInfo(@PathVariable int displayInfoId) {
		ProductDetailResponseDto result = new ProductDetailResponseDto();

		result.setAverageScore(displayInfoId);
		result.setComments(productDetailService.findAllCommentsById(displayInfoId));
		result.setDisplayInfo(productDetailService.findDisplayInfoById(displayInfoId));
		result.setDisplayInfoImage(productDetailService.findDisplayInfoImageById(displayInfoId));
		result.setProductImages(productDetailService.findAllProductImagesById(displayInfoId));
		result.setProductPrices(productDetailService.findAllPricesById(displayInfoId));

		return result;
	}
}
