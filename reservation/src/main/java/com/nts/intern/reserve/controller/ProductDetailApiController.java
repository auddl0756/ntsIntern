package com.nts.intern.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.EtcImageDto;
import com.nts.intern.reserve.dto.ProductDetailResponseDto;
import com.nts.intern.reserve.service.EtcImageService;
import com.nts.intern.reserve.service.ProductDetailService;

@RestController
public class ProductDetailApiController {
	@Autowired
	private ProductDetailService productDetailService;

	@Autowired
	private EtcImageService etcImageService;

	@GetMapping(value = "/api/products/{displayInfoId}")
	public ProductDetailResponseDto findDetailInfo(@PathVariable int displayInfoId) {
		ProductDetailResponseDto result = new ProductDetailResponseDto();

		result.setAverageScore(productDetailService.findAverageScore(displayInfoId));
		result.setComments(productDetailService.findAllCommentsById(displayInfoId));
		result.setDisplayInfo(productDetailService.findDisplayInfoById(displayInfoId));
		result.setDisplayInfoImage(productDetailService.findDisplayInfoImageById(displayInfoId));
		result.setProductImages(productDetailService.findAllProductImagesById(displayInfoId));
		result.setProductPrices(productDetailService.findAllPricesById(displayInfoId));

		return result;
	}

	@GetMapping("/api/products/etcImages/{displayInfoId}")
	public List<EtcImageDto> findEtcImages(@PathVariable int displayInfoId) {
		return etcImageService.findAllEtcImagesById(displayInfoId);
	}
}
