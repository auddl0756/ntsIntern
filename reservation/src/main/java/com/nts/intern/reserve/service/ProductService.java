package com.nts.intern.reserve.service;

import java.util.List;
import java.util.Set;

import com.nts.intern.reserve.dto.ProductItemDto;

public interface ProductService {
	List<ProductItemDto> findWithPaging(Set<Integer> displayInfoIds, int limit);

	List<ProductItemDto> findWithPagingAndCategory(Set<Integer> displayInfoIds, int limit, int categoryId);

	int getCount();

	int getCountByCategory(int categoryId);
}
