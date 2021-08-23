package com.nts.intern.reserve.dto.reserve;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
