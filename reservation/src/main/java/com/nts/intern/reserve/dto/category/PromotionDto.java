package com.nts.intern.reserve.dto.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PromotionDto {
	private int promotionId;
	private int productId;
	private String productImageUrl;
}
