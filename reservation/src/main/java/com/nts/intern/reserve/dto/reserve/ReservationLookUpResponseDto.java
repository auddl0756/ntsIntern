package com.nts.intern.reserve.dto.reserve;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ReservationLookUpResponseDto {
	private List<ReservationResponseDto> reservations;
	private int totalReservationCount;
}
