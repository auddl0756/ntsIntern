package com.nts.intern.reserve.repository.sql.reserve;

public class ReservationPriceRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT (pp.price * (100 - pp.discount_rate) / 100) as discountedProductPrice," +
		"pp.discount_rate as discountRate," +
		"pp.price_type_name as priceTypeName " +
		
		"FROM product_price pp " +
		"INNER JOIN product pd on pd.id=  pp.product_id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		
		"WHERE di.id  = :displayInfoId;";
	
	public static final String FIND_MIN_PRICE
	= "SELECT MIN(pp.price * (100 - pp.discount_rate) / 100) "+
	
		"FROM product_price pp " +
		
		"INNER JOIN product pd on pd.id=  pp.product_id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		"WHERE di.id  = :displayInfoId;";
}
