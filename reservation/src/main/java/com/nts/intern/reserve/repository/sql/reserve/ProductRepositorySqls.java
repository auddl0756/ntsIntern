package com.nts.intern.reserve.repository.sql.reserve;

public class ProductRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT di.id AS displayInfoId," +
		"di.place_name AS placeName," +
		"di.opening_hours AS openingHours," +
		
		"pd.description AS productDescription," +
		"pd.id AS productId," +
		
		"fi.save_file_name AS saveFileName "+
		
		"FROM display_info di " +
		"INNER JOIN product pd on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pi.product_id = pd.id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE di.id  = :displayInfoId " +
		"AND pi.type = \'ma\'";
}
