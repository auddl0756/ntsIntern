package com.nts.intern.reserve.dto.detail;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DisplayInfoImageDto {
	private int displayInfoImageId;
	private int displayInfoId;
	private int fileId;

	private String contentType;
	private boolean deleteFlag;
	private String fileName;
	private String saveFileName;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
