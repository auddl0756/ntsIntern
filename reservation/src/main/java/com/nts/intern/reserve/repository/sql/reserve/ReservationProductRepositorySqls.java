package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationProductRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT di.id as displayInfoId," +
		"di.place_name as placeName," +
		"di.opening_hours as openingHours," +
		
		"pd.description as productDescription," +
		
		"fi.save_file_name as saveFileName "+
		
		"FROM display_info di " +
		"INNER JOIN product pd on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pi.product_id = pd.id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE di.id  = :displayInfoId " +
		"LIMIT :limit";
}
