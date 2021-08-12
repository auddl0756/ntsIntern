package com.nts.intern.reserve.repository.sql.category;

public class ProductRepositorySqls {
	public static final String SELECT_WITH_PAGING_AND_CATEGORY 
	= "SELECT di.id as displayInfoId, " +
		"di.place_name as placeName, " +
		"pd.content as productContent, " +
		"pd.description as productDescription, " +
		"pd.id as productId, "+
		"fi.save_file_name as productImageUrl " +
		"FROM product pd " +
		"INNER JOIN display_info di on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pd.id = pi.product_id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE pd.category_id = :categoryId " +
		"AND (di.id < :excludeFirst or di.id > :excludeLast) " +
		"GROUP BY di.id " +
		"ORDER BY di.id DESC " +
		"LIMIT :limit;";

	public static final String SELECT_WITH_PAGING 
	= "SELECT di.id as displayInfoId, " +
		"di.place_name as placeName, " +
		"pd.content as productContent, " +
		"pd.description as productDescription, " +
		"pd.id as productId, "+
		"fi.save_file_name as productImageUrl " +
		"FROM product pd " +
		"INNER JOIN display_info di on pd.id = di.product_id " +
		"INNER JOIN product_image pi on pd.id = pi.product_id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE (di.id < :excludeFirst or di.id > :excludeLast) " +
		"GROUP BY di.id " +
		"ORDER BY di.id DESC " +
		"LIMIT :limit;";
	
	public static final String COUNT_BY_CATEGORY 
	="SELECT count( DISTINCT di.id) " + 
		"FROM product pd " + 
		"INNER JOIN display_info di on pd.id=di.product_id " + 
		"INNER JOIN product_image pi on pd.id=pi.product_id " + 
		"INNER JOIN file_info fi on fi.id=pi.file_id " + 
		"WHERE pd.category_id = :categoryId " + 
		"AND pi.type =\'th\';";
	
	public static final String COUNT_ALL
	="SELECT count( DISTINCT di.id) " + 
		"FROM product pd " + 
		"INNER JOIN display_info di on pd.id=di.product_id " + 
		"INNER JOIN product_image pi on pd.id=pi.product_id " + 
		"INNER JOIN file_info fi on fi.id=pi.file_id " + 
		"WHERE pi.type =\'th\';";
}
