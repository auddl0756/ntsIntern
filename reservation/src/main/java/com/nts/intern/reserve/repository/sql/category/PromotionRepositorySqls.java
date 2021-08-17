package com.nts.intern.reserve.repository.sql.category;

public class PromotionRepositorySqls {
	public static final String SELECT_ALL =
		"SELECT pm.id as promotion_id ,"
			+ " pd.id as product_id ,"
			+ " fi.save_file_name as product_image_url "
			+ "FROM product pd "
			+ "INNER JOIN promotion pm on pd.id=pm.product_id "
			+ "INNER JOIN product_image pi on pd.id=pi.product_id "
			+ "INNER JOIN file_info fi on fi.id=pi.file_id "
			+ "WHERE pi.type=\'th\';";
}
