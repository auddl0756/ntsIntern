package com.nts.intern.reserve.dto.detail;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CommentImageDto {
	private int imageId;
	private int fileId;
	private int reservationInfoId;
	private int reservationUserCommentId;

	private String contentType;
	private boolean deleteFlag;
	private String fileName;
	private String saveFileName;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
