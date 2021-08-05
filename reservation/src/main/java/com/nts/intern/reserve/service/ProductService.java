package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.dto.ProductItemDto;

public interface ProductService {
	List<ProductItemDto> findWithPaging(int excludeFirst, int excludeLast, int limit);

	List<ProductItemDto> findWithPagingAndCategory(int excludeFirst, int excludeLast, int limit, int categoryId);

	int getCount();

	int getCountByCategory(int categoryId);
}
