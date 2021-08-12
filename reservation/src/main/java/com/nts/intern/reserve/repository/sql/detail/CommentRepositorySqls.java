package com.nts.intern.reserve.repository.sql.detail;

public class CommentRepositorySqls {
	public static final String FIND_ALL_BY_ID 
	= "SELECT rev_com.id as commentId," +
		"rev_com.comment as comment," +
		"rev_com.create_date as createDate,"+
		"rev_com.modify_date as modifyDate,"+
		"rev_com.score as score,"+
		
		"rev_info.product_id as productId,"+
		"rev_info.reservation_date as reservationDate,"+
		"rev_info.reservation_email as reservationEmail,"+
		"rev_info.id as reservationInfoId,"+
		"rev_info.reservation_name as reservationName,"+
		"rev_info.reservation_tel as reservationTelephone "+
				
		"FROM reservation_info rev_info " +
		"INNER JOIN reservation_user_comment rev_com on rev_com.reservation_info_id = rev_info.id " +
		"WHERE rev_info.display_info_id  = :displayInfoId;";
}
