package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.ProductDto;

public interface ProductService {
	public List<ProductDto> findWithPaging(int start,int limit);
	public List<ProductDto> findWithPagingAndCategory(int start,int limit,int categoryId);
}
