package com.nts.intern.reserve.dto.reserve;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductDto {
	private int productId;
	private int displayInfoId;
	private String productDescription;
	private String saveFileName;
	private String placeName;
	private String openingHours;
	private List<PriceDto> priceInfos;
	private String reservationDate;
}
