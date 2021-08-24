package com.nts.intern.reserve.repository.sql.reserve;

public class PriceRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT (pp.price * (100 - pp.discount_rate) / 100) as discountedProductPrice," +
		"pp.discount_rate AS discountRate," +
		"pp.price_type_name AS priceTypeName," +
		"pp.id AS productPriceId " +
		
		"FROM product_price pp " +
		"INNER JOIN product pd on pd.id=  pp.product_id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		
		"WHERE di.id  = :displayInfoId;";
}
