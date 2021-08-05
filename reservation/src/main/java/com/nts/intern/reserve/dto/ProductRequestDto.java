package com.nts.intern.reserve.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProductRequestDto {
	private int categoryId;
	private Set<Integer> displayInfoIds;
}
