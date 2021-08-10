package com.nts.intern.reserve.repository.sql;

public class EtcImageRepositorySqls {
	public static final String FIND_BY_ID
	= "SELECT fi.save_file_name as saveFileName " +
		"FROM product_image pi " +
		"JOIN product pd on pi.product_id = pd.id " +
		"JOIN display_info di on di.product_id = pd.id " +
		"JOIN file_info fi on fi.id = pi.file_id " +
		"WHERE di.id =:displayInfoId " +
		"AND pi.type=\'et\';";
}
