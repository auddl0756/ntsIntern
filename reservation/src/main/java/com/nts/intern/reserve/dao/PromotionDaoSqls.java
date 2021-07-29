package com.nts.intern.reserve.dao;

public class PromotionDaoSqls {
	public static final String SELECT_ALL 
		= "SELECT pm.id as promotion_id , pd.id as product_id , fi.save_file_name as product_image_url "
			+ "FROM product pd, promotion pm,product_image pi,file_info fi " + 
		"WHERE pd.id = pm.product_id " + 
		"AND pd.id=pi.product_id " + 
		"AND pi.file_id = fi.id "+
		"AND pi.type=\'th\';";
}
