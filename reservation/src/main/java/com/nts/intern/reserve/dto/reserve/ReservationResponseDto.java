package com.nts.intern.reserve.dto.reserve;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReservationResponseDto {
	DisplayInfoDto displayInfo;
	
	private boolean cancleYn;
	private String createDate;
	private String modifyDate;
	private int displayInfoId;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	
	private int totalPrice;
}
