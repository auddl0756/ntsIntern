package com.nts.intern.reserve.dto.reserve;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PriceDto {
	private int productPriceId;
	private int discountedProductPrice;
	private double discountRate;
	private String priceTypeName;

	private PriceTypeMap priceTypeMap;

	@Setter(AccessLevel.NONE)
	private String actualPriceTypeName;

	public void makeActualPriceTypeName() {
		this.priceTypeMap = PriceTypeMap.valueOf(priceTypeName);
		this.actualPriceTypeName = this.priceTypeMap.getActualName();
	}
}
