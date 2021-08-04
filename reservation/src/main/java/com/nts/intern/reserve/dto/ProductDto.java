package com.nts.intern.reserve.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductDto {
	private List<ProductItemDto> items;
	private int totalCount;
}
