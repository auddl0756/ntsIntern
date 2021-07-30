package com.nts.intern.reserve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductDto {
	private String productDescription;
	private String productContent;
	private String placeName;
	private String productImageUrl;
}
