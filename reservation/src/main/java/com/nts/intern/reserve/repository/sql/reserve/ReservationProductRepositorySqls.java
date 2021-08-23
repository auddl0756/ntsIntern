package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationProductRepositorySqls {
	public static final String SELECT_LATEST_RESERVATION_ID = 
		"SELECT MAX(reservation_info.id) "+
		"FROM reservation_info;";
}
