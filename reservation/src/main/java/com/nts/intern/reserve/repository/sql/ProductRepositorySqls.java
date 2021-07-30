package com.nts.intern.reserve.repository.sql;

public class ProductRepositorySqls {
	public static final String SELECT_WITH_PAGING_AND_CATEGORY 
	= "SELECT pd.description as product_description ," +
		"pd.content as product_content, " +
		"di.place_name as place_name, " +
		"fi.save_file_name as product_image_url " +
		"FROM product pd\r\n" +
		"INNER JOIN display_info di on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pd.id = pi.product_id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE pd.category_id = :categoryId " +
		"GROUP BY pd.id " +
		"ORDER BY pd.id DESC " +
		"LIMIT :start ,:limit;";

	public static final String SELECT_WITH_PAGING 
	= "SELECT pd.description as product_description, " +
		"pd.content as product_content, " +
		"di.place_name as place_name, " +
		"fi.save_file_name as product_image_url " +
		"FROM product pd " +
		"INNER JOIN display_info di on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pd.id = pi.product_id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"GROUP BY pd.id " +
		"ORDER BY pd.id DESC " +
		"LIMIT :start ,:limit;";
	
	public static final String COUNT_BY_CATEGORY 
	="SELECT count( DISTINCT pd.id) " + 
		"FROM product pd " + 
		"INNER JOIN display_info di on pd.id=di.product_id " + 
		"INNER JOIN product_image pi on pd.id=pi.product_id " + 
		"INNER JOIN file_info fi on fi.id=pi.file_id " + 
		"WHERE pd.category_id = :categoryId " + 
		"AND pi.type =\'th\';";
	
	public static final String COUNT_ALL
	="SELECT count( DISTINCT pd.id) " + 
		"FROM product pd " + 
		"INNER JOIN display_info di on pd.id=di.product_id " + 
		"INNER JOIN product_image pi on pd.id=pi.product_id " + 
		"INNER JOIN file_info fi on fi.id=pi.file_id " + 
		"WHERE pi.type =\'th\';";
}
