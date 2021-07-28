package com.nts.intern.reserve.dto;

import java.time.LocalDateTime;

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
	private String description;
	private String content;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
