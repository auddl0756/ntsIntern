package com.nts.intern.reserve.dto.detail;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductDetailResponseDto {
	private double averageScore;
	private List<CommentDto> comments;
	private int totalCommentsCount;
	private DisplayInfoDto displayInfo;
	private DisplayInfoImageDto displayInfoImage;
	private List<ProductImageDto> productImages;
	private List<ProductPriceDto> productPrices;
}
