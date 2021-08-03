package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.ProductDto;

public interface ProductService {
	List<ProductDto> findWithPaging(int start, int limit);

	List<ProductDto> findWithPagingAndCategory(int start, int limit, int categoryId);

	int getSize();

	int getSizeByCategory(int categoryId);
}
