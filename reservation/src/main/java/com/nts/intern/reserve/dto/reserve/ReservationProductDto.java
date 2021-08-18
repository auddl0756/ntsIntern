package com.nts.intern.reserve.dto.reserve;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReservationProductDto {
	private int displayInfoId;
	private String productDescription;
	private String priceTypeName;
	private int productPrice;
	private double discountRate;
	private String saveFileName;
}
