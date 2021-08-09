package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.CommentDto;
import com.nts.intern.reserve.dto.DisplayInfoDto;
import com.nts.intern.reserve.dto.DisplayInfoImageDto;
import com.nts.intern.reserve.dto.ProductImageDto;
import com.nts.intern.reserve.dto.ProductPriceDto;

public interface ProductDetailService {
	double findAverageScore(int displayInfoId);

	List<CommentDto> findAllCommentsById(int displayInfoId);

	DisplayInfoDto findDisplayInfoById(int displayInfoId);

	DisplayInfoImageDto findDisplayInfoImageById(int displayInfo);

	List<ProductImageDto> findAllProductImagesById(int displayInfoId);

	List<ProductPriceDto> findAllPricesById(int displayInfoId);

}
