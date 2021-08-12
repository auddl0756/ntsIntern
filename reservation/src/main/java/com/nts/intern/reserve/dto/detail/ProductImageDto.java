package com.nts.intern.reserve.dto.detail;

import lombok.Setter;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductImageDto {
	private int productImageId;
	private int productId;
	private int fileInfoId;

	private String contentType;
	private boolean deleteFlag;
	private String fileName;
	private String saveFileName;
	private String type;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
