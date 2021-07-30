package com.nts.intern.reserve.repository.sql;

public class ProductRepositorySqls {
	public static final String SELECT_WITH_PAGING_AND_CATEGORY 
	= "SELECT pd.description as product_description ,\r\n" +
		"pd.content as product_content, \r\n" +
		"di.place_name as place_name, \r\n" +
		"fi.save_file_name as product_image_url \r\n" +
		"FROM product pd\r\n" +
		"INNER JOIN display_info di on pd.id = di.product_id\r\n" +
		"INNER JOIN product_image pi on pd.id = pi.product_id\r\n" +
		"INNER JOIN file_info fi on fi.id = pi.file_id\r\n" +
		"WHERE pd.category_id = :categoryId \r\n" +
		"GROUP BY pd.id\r\n" +
		"ORDER BY pd.id DESC\r\n" +
		"LIMIT :start ,:limit;";

	public static final String SELECT_WITH_PAGING 
	= "SELECT pd.description as product_description ,\r\n" +
		"pd.content as product_content, \r\n" +
		"di.place_name as place_name, \r\n" +
		"fi.save_file_name as product_image_url \r\n" +
		"FROM product pd\r\n" +
		"INNER JOIN display_info di on pd.id = di.product_id\r\n" +
		"INNER JOIN product_image pi on pd.id = pi.product_id\r\n" +
		"INNER JOIN file_info fi on fi.id = pi.file_id\r\n" +
		"GROUP BY pd.id\r\n" +
		"ORDER BY pd.id DESC\r\n" +
		"LIMIT :start ,:limit;";
	
	public static final String COUNT_WITH_CATEGORY 
	="SELECT count( DISTINCT pd.id)\r\n" + 
		"FROM product pd \r\n" + 
		"INNER JOIN display_info di on pd.id=di.product_id \r\n" + 
		"INNER JOIN product_image pi on pd.id=pi.product_id \r\n" + 
		"INNER JOIN file_info fi on fi.id=pi.file_id\r\n" + 
		"WHERE pd.category_id = :categoryId \r\n" + 
		"AND pi.type =\'th\';";
	
	public static final String COUNT_ALL
	="SELECT count( DISTINCT pd.id)\r\n" + 
		"FROM product pd \r\n" + 
		"INNER JOIN display_info di on pd.id=di.product_id \r\n" + 
		"INNER JOIN product_image pi on pd.id=pi.product_id \r\n" + 
		"INNER JOIN file_info fi on fi.id=pi.file_id\r\n" + 
		"WHERE pi.type =\'th\';";
}
