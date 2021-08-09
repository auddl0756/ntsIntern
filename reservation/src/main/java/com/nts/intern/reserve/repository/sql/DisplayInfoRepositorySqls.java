package com.nts.intern.reserve.repository.sql;

public class DisplayInfoRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT dInfo.id as displayInfoId," +
		"dInfo.product_id as productId," +
		"dInfo.create_date as createDate," +
		"dInfo.modify_date as modifyDate," +
		"dInfo.email as email," +
		"dInfo.homepage as homepage," +
		"dInfo.opening_hours as openingHours," +
		"dInfo.place_lot as placeLot," +
		"dInfo.place_street as placeStreet," +
		"dInfo.place_name as placeName," +
		"dInfo.tel as telephone," +
		
		"pd.id as productId," +
		"pd.content as productContent," +
		"pd.description as productDescription," +
		"pd.event as productEvent, " +
		"ct.id as categoryId," +
		"ct.name as categoryName " +
		
		"FROM display_info dInfo " +
		"INNER JOIN product pd on pd.id = dInfo.product_id " +
		"INNER JOIN category ct on ct.id = pd.category_id " +
		"WHERE dInfo.id  = :displayInfoId;";
}
