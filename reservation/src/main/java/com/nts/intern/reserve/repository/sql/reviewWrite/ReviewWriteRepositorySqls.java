package com.nts.intern.reserve.repository.sql.reviewWrite;

public class ReviewWriteRepositorySqls {
	public static final String FIND_BY_ID =
		"SELECT pd.description AS productDescription," +
		"ri.product_id AS productId " +
		"FROM product pd " +
		"JOIN reservation_info ri ON ri.product_id = pd.id " +
		"WHERE ri.id = :reservationInfoId ;";	
}
