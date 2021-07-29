package com.nts.intern.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PromotionDto {
	private int promotionId;
	private int productId;
	private String productImageUrl;
}
