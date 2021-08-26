package com.nts.intern.reserve.repository.sql.reviewWrite;

public class ProductDescriptionRepositorySqls {
	public static final String FIND_BY_ID =
		"SELECT pd.description AS productDescription " +
		"FROM product pd " +
		"JOIN reservation_info ri ON ri.product_id = pd.id " +
		"WHERE ri.id = :reservationInfoId ;";	
}
