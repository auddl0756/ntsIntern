package com.nts.intern.reserve.dto.reserve;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReservationPriceDto {
	private int discountedProductPrice;
	private double discountRate;
	private String priceTypeName;

	@Setter(AccessLevel.NONE)
	private String actualPriceTypeName;

	private final static Map<String, String> priceTypeMap = new HashMap<String, String>() {
		{
			put("Y", "청소년");
			put("A", "성인");
			put("B", "유아");
			put("D", "장애인");

			put("E", "얼리버드");
			put("SET", "세트");

			put("V", "VIP석");
			put("R", "R석");
			put("S", "S석");
		}
	};

	public void makeActualPriceTypeName() {
		this.actualPriceTypeName = priceTypeMap.getOrDefault(this.priceTypeName, "NONE");
	}
}
