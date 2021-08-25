package com.nts.intern.reserve.dto.reserve;

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
	
	private boolean cancleYn;
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
}
