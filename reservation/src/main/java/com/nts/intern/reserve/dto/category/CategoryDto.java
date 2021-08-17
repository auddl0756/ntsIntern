package com.nts.intern.reserve.dto.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CategoryDto {
	private int id;
	private String name;
	private int count;
}
