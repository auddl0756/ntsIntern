package com.nts.intern.reserve.dto.reserve;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ReservationParam {
	@NonNull
	private int displayInfoId;
	
	@NonNull
	private int productId;
	
	private List<ReservationPrice> reservationPrices;
	
	@NonNull
	private String reservationEmail;
	
	@NonNull
	private String reservationName;
	
	@NonNull
	private String reservationTel;
	
	@NonNull
	private String reservationDate;
	
	@NonNull
	private String createDate;
	
	@NonNull 
	private String modifyDate;
	
	@NonNull
	private boolean cancelFlag;
}
