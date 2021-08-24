package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationProductRepositorySqls {
	public static final String SELECT_LATEST_RESERVATION_ID = 
		"SELECT MAX(reservation_info.id) "+
		"FROM reservation_info;";
	
	public static final String SELECT_DISPLAY_INFO_BY_ID =
		"SELECT ct.id categoryId," + 
		"ct.name categoryName," + 
		"di.create_date createDate," + 
		"di.id displayInfoId," + 
		"di.email email," + 
		"di.homepage homepage," + 
		"di.modify_date modifyDate," + 
		"di.opening_hours openingHours," + 
		"di.place_lot placeLot," + 
		"di.place_name placeName," + 
		"di.place_street placeStreet," + 
		"di.tel telephone," + 
		"pd.content productContent," + 
		"pd.description productDescription," + 
		"pd.event productEvent," + 
		"pd.id productId " + 
		"FROM display_info as di " + 
		"JOIN product as pd ON di.product_id = pd.id " + 
		"JOIN category as ct ON pd.category_id = ct.id " +
		"WHERE di.id = :displayInfoId;";
	
	public static final String SELECT_ALL_RESERVATIONS = 
		"SELECT id reservationInfoId," + 
		"product_id productId," + 
		"display_info_id displayInfoId," + 
		"reservation_name reservationName," + 
		"reservation_tel reservationTelephone," + 
		"reservation_email reservationEmail," + 
		"reservation_date reservationDate," + 
		"cancel_flag cancleYn," + 
		"create_date createDate," + 
		"modify_date modifyDate " + 
		"FROM reservation_info as ri " + 
		"where ri.reservation_email = :email;";
	
	
	public static final String GET_TOTAL_PRICE_BY_ID= 
		"SELECT SUM(rip.count * pp.price) " + 
		"FROM reservation_info as ri " + 
		"JOIN reservation_info_price as rip ON ri.id = rip.reservation_info_id " + 
		"JOIN product_price as pp ON pp.id = rip.product_price_id " + 
		"WHERE ri.id = :reservationInfoId;";
}
