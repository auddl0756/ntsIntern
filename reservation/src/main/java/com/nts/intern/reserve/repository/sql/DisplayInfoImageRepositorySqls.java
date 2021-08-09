package com.nts.intern.reserve.repository.sql;

public class DisplayInfoImageRepositorySqls {
	public static final String FIND_BY_ID 
	= "SELECT dInfo.id as displayInfoId," +
		
		"dii.id as displayInfoImageId," + 

		"fi.id as fileId," +
		"fi.file_name as fileName," +
		"fi.content_type as contentType," +
		"fi.create_date as createDate," +
		"fi.modify_date as modifyDate," +
		"fi.delete_flag as deleteFlag," +
		"fi.save_file_name as saveFileName " +
				
		"FROM display_info dInfo " +
		"INNER JOIN display_info_image dii on dii.display_info_id = dInfo.id " +
		"INNER JOIN file_info fi on fi.id = dii.file_id " +
		"WHERE dInfo.id  = :displayInfoId;";
}
