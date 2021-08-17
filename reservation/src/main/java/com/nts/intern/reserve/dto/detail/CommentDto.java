package com.nts.intern.reserve.dto.detail;

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

	private double score;
	private String comment;
	private String reservationName;
	private String reservationEmail;
	private String reservationTelephone;
	private List<CommentImageDto> commentImages;

	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	private String maskedReservationEmail;

	public void createMaskedReservationEmail() {
		if (this.getReservationEmail().length() < 4) {
			this.maskedReservationEmail = "****";
		} else {
			this.maskedReservationEmail = this.getReservationEmail().substring(0, 4) + "****";
		}
	}
}
