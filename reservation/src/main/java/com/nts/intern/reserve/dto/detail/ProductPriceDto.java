package com.nts.intern.reserve.dto.detail;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductPriceDto {
	private int productPriceId;
	private int productId;

	private int discountRate;
	private int price;
	private String priceTypeName;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
