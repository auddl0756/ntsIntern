package com.nts.intern.reserve.repository.sql.detail;

public class CommentImageRepositorySqls {
	public static final String FIND_ALL_BY_ID 
	= "SELECT rev_info.id as reservationInfoId," +
	
		"rev_com.id as reservationUserCommentId," +
		
		"fi.id as fileId," +
		"fi.file_name as fileName," +
		"fi.content_type as contentType," +
		"fi.delete_flag as deleteFlag," +
		"fi.save_file_name as saveFileName," +
		"fi.create_date as createDate," +
		"fi.modify_date as modifyDate," +
		
		"rev_com_img.id as imageId " +
		
		"FROM reservation_info rev_info " +
		"INNER JOIN reservation_user_comment rev_com on rev_com.reservation_info_id = rev_info.id " +
		"INNER JOIN reservation_user_comment_image rev_com_img on rev_com_img.reservation_user_comment_id = rev_com.id " +
		"INNER JOIN file_info fi on fi.id = rev_com_img.file_id " +
		
		"WHERE rev_com.id  = :commentId;";
}
