package com.nts.intern.reserve.repository.sql.util;

public class FileLoadRepositorySqls {
	public static final String FIND_UPLOADED_IMG_BY_ID =
		"SELECT fi.file_name AS fileName," +
		"fi.save_file_name AS saveFileName," +
		"fi.content_type AS contentType " +
		"FROM file_info fi " +
		"JOIN reservation_user_comment_image com_img ON fi.reservation_user_comment_image_id = com_img.id " +
		"WHERE com_img.id = :commentImageId;";	
	
	public static final String FIND_IMG_BY_COMMENT_ID =
		"SELECT fi.file_name AS fileName," +
		"fi.save_file_name AS saveFileName," +
		"fi.content_type AS contentType " +
		"FROM file_info fi " +
		"JOIN reservation_user_comment_image comment_imgage ON fi.reservation_user_comment_image_id = com_img.id " +
		"JOIN reservation_user_comment comment ON comment.id = comment_imgage.reservation_user_comment_id " +
		"WHERE comment.id = :commentId;";
	
	public static final String FIND_IMG_BY_DISPLAY_ID = 
		"SELECT fi.file_name AS fileName," + 
		"fi.save_file_name AS saveFileName," + 
		"fi.content_type AS contentType " + 
		"FROM product pd " + 
		"INNER JOIN display_info di on pd.id = di.product_id " + 
		"INNER JOIN product_image pi on pd.id = pi.product_id " + 
		"INNER JOIN file_info fi on fi.id = pi.file_id " + 
		"WHERE di.id = :displayInfoId " + 
		"AND pi.type= \'th\';";
	
	public static final String FIND_IMG_BY_PRODUCT_ID = 
		"SELECT fi.file_name AS fileName," + 
		"fi.save_file_name AS saveFileName," + 
		"fi.content_type AS contentType " + 
		"FROM product pd " +  
		"INNER JOIN product_image pi on pd.id = pi.product_id " + 
		"INNER JOIN file_info fi on fi.id = pi.file_id " + 
		"WHERE pd.id = :productId " + 
		"AND pi.type= \'th\';";
}
