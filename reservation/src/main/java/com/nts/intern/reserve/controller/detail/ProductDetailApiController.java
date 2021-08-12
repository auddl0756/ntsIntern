package com.nts.intern.reserve.controller.detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.detail.EtcImageDto;
import com.nts.intern.reserve.dto.detail.ProductDetailResponseDto;
import com.nts.intern.reserve.service.detail.EtcImageService;
import com.nts.intern.reserve.service.detail.ProductDetailService;

@RestController
public class ProductDetailApiController {
	@Autowired
	private ProductDetailService productDetailService;

	@Autowired
	private EtcImageService etcImageService;

	@GetMapping(value = "/api/products/{displayInfoId}")
	public ProductDetailResponseDto findInitialDetailInfo(@PathVariable int displayInfoId) {
		ProductDetailResponseDto result = new ProductDetailResponseDto();

		result.setAverageScore(productDetailService.findInitialAverageScore(displayInfoId));
		result.setComments(productDetailService.findInitialCommentsById(displayInfoId));
		result.setDisplayInfo(productDetailService.findDisplayInfoById(displayInfoId));
		result.setDisplayInfoImage(productDetailService.findDisplayInfoImageById(displayInfoId));
		result.setProductImages(productDetailService.findAllProductImagesById(displayInfoId));
		result.setProductPrices(productDetailService.findAllPricesById(displayInfoId));

		return result;
	}

	@GetMapping(value = "/api/products/all/{displayInfoId}")
	public ProductDetailResponseDto findAllDetailInfo(@PathVariable int displayInfoId) {
		ProductDetailResponseDto result = new ProductDetailResponseDto();

		result.setAverageScore(productDetailService.findTotalAverageScore(displayInfoId));
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
