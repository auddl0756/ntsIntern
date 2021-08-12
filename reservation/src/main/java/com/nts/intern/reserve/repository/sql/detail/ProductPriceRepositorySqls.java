package com.nts.intern.reserve.repository.sql.detail;

public class ProductPriceRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT price.id as productPriceId," +
		"price.product_id as productId," +
		"price.price_type_name as priceTypeName," +
		"price.price as price," +
		"price.discount_rate as discountRate," +
		"price.create_date as createDate," +
		"price.modify_date as modifyDate " +
		
		"FROM product_price price " +
		"INNER JOIN product pd on pd.id = price.product_id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		"WHERE di.id  = :displayInfoId;";
		
}
