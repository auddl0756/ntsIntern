package com.nts.intern.reserve.dao;

public class ProductDaoSqls {
	public static final String SELECT_PAGING = "SELECT description,content FROM product ORDER BY id DESC limit :start, :limit";
	public static final String SELECT_COUNT = "SELECT count(*) FROM product";
}
