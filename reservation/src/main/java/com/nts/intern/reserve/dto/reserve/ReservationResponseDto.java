package com.nts.intern.reserve.dto.reserve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReservationResponseDto {
	private int categoryId;
	private String categoryName;
	private String displayInfoCreateDate;
	private String displayInfoModifyDate;
	private String email;
	private String homepage;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String telephone;
	private String productContent;
	private String productDescription;
	private String productEvent;

	private boolean cancelYn;
	private String reservationCreateDate;
	private String reservationModifyDate;
	private int displayInfoId;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;

	private int totalPrice;

	@Getter(AccessLevel.NONE)
	private ReservationType type;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

	public ReservationType getType() {
		if (this.cancelYn) {
			return ReservationType.CANCELED;
		}

		LocalDateTime nowTime = LocalDateTime.now();
		LocalDateTime reservationDate = LocalDateTime.parse(this.reservationDate, FORMATTER);

		if (nowTime.isAfter(reservationDate)) {
			return ReservationType.USED;
		} else {
			return ReservationType.CONFIRMED;
		}
	}
}
