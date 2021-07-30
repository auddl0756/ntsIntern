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
public class ProductDto {
	private String productDescription;
	private String productContent;
	private String placeName;
	private String productImageUrl;
}
