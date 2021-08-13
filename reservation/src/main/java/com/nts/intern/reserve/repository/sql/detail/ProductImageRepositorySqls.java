package com.nts.intern.reserve.repository.sql.detail;

public class ProductImageRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT pd.id as productId," +
		
		"pi.id as productImageId," +
		"pi.type as type," + 

		"fi.id as fileInfoId," +
		"fi.file_name as fileName," +
		"fi.content_type as contentType," +
		"fi.create_date as createDate," +
		"fi.modify_date as modifyDate," +
		"fi.delete_flag as deleteFlag," +
		"fi.save_file_name as saveFileName " +
				
		"FROM product pd " +
		"INNER JOIN product_image pi on pd.id = pi.product_id " +
		"INNER JOIN file_info fi on fi.id = pi.file_id " +
		"INNER JOIN display_info di on di.product_id = pd.id " +
		"WHERE di.id  = :displayInfoId;";
}
