package com.nts.intern.reserve.dto.reserve;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReservationParam {
	private int displayInfoId;
	private int productId;
	private List<ReservationPrice> reservationPrices;
	private String reservationEmail;
	private String reservationName;
	private String reservationTel;
	private String reservationDate;
	private boolean cancelFlag;
}
