package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationProductRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT di.id as displayInfoId," +
		
		"pd.description as productDescription," +
		
		"pp.price_type_name as priceTypeName," +
		"pp.price as productPrice," +
		"pp.discount_rate as discountRate," +
		
		"fi.save_file_name as saveFileName "+
		
		"FROM display_info di " +
		"INNER JOIN product pd on pd.id = di.product_id " +
		"INNER JOIN product_price pp on pp.product_id = pd.id " +
		"INNER JOIN product_image pi on pi.product_id=  pd.id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE di.id  = :displayInfoId;";
}
