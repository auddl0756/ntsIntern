package com.nts.intern.reserve.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CommentDto {
	private int commentId;
	private int productId;
	private int reservationInfoId;

	private int score;
	private String comment;
	private String reservationName;
	private String reservationEmail;
	private String reservationTelephone;
	private List<CommentImageDto> commentImages;

	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
