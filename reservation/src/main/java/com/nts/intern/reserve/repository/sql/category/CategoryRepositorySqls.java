package com.nts.intern.reserve.repository.sql.category;

public class CategoryRepositorySqls {
	public static final String SELECT_ALL_CATEGORIES 
	= "SELECT ct.id as id," +
		"ct.name as name," +
		"count(*) as count " +
		"FROM product pd " +
		"INNER JOIN category ct on pd.category_id = ct.id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		"GROUP BY ct.id;";
}
