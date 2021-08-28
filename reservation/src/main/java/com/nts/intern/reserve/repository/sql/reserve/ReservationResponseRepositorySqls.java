package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationResponseRepositorySqls {
	public static final String SELECT_ALL_RESERVATIONS = 
		"SELECT ct.id AS categoryId," + 
		"ct.name AS categoryName," + 
		"di.email AS email," + 
		"di.homepage AS homepage," + 
		"di.create_date AS displayInfoCreateDate," +
		"di.modify_date AS displayInfoModifyDate," +
		"di.opening_hours AS openingHours," + 
		"di.place_lot AS placeLot," + 
		"di.place_name AS placeName," + 
		"di.place_street AS placeStreet," + 
		"di.tel AS telephone," + 
		"pd.content AS productContent," + 
		"pd.description AS productDescription," + 
		"pd.event AS productEvent," + 
		"ri.id AS reservationInfoId," + 
		"pd.id AS productId," + 
		"di.id AS displayInfoId," + 
		"reservation_name AS reservationName," + 
		"reservation_tel AS reservationTelephone," + 
		"reservation_email AS reservationEmail," + 
		"reservation_date AS reservationDate," + 
		"cancel_flag AS cancelYn," + 
		"ri.create_date AS reservationCreateDate," + 
		"ri.modify_date AS reservationModifyDate," + 
		"SUM(rip.count * pp.price) AS totalPrice " + 
		
		"FROM reservation_info ri " + 
		"JOIN reservation_info_price rip ON ri.id = rip.reservation_info_id " + 
		"JOIN product_price pp ON pp.id = rip.product_price_id " + 
		"JOIN product pd ON pp.product_id = pd.id " + 
		"JOIN category ct ON pd.category_id = ct.id " + 
		"JOIN display_info di ON di.product_id = pd.id " + 
		
		"WHERE ri.reservation_email = :email " + 
		"GROUP BY ri.id;";
}
