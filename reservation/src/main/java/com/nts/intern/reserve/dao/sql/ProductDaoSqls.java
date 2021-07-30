package com.nts.intern.reserve.dao.sql;

public class ProductDaoSqls {
	//public static final String SELECT_PAGING = "SELECT description,content FROM product ORDER BY id DESC limit :start, :limit";

	public static final String SELECT_WITH_PAGING_AND_CATEGORY = "SELECT  pd.description as product_description ," +
		"pd.content as product_content, " +
		"di.place_name as place_name, " +
		"fi.save_file_name as product_image_url " +
		"FROM product pd , display_info di , file_info fi  ,  product_image pi\r\n" +
		"WHERE pd.category_id =:categoryId \r\n" +
		"AND pd.id  = di.product_id \r\n" +
		"AND pd.id = pi.product_id\r\n" +
		"AND pi.file_id = fi.id \r\n" +
		"GROUP BY pd.id " +
		"ORDER BY pd.id DESC \r\n" +
		"LIMIT :start, :limit;";

	public static final String SELECT_WITH_PAGING = "SELECT  pd.description as product_description ," +
		"pd.content as product_content, " +
		"di.place_name as place_name, " +
		"fi.save_file_name as product_image_url " +
		"FROM product pd , display_info di , file_info fi  ,  product_image pi\r\n" +
		"WHERE pd.category_id =1 \r\n" +
		"AND pi.type ='th'\r\n" +
		"AND pd.id  = di.product_id \r\n" +
		"AND pd.id = pi.product_id\r\n" +
		"AND pi.file_id = fi.id \r\n" +
		"GROUP BY pd.id " +
		"ORDER BY pd.id DESC \r\n" +
		
		"LIMIT :start, :limit;";
}
