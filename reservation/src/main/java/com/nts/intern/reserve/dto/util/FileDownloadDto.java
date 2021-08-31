package com.nts.intern.reserve.dto.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FileDownloadDto {
	private String fileName;
	private String saveFileName;
	private String contentType;
}
