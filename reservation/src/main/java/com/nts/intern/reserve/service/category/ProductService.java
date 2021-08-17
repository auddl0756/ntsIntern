package com.nts.intern.reserve.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.category.ProductItemDto;
import com.nts.intern.reserve.repository.category.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<ProductItemDto> findWithPaging(int excludeFirst, int excludeLast, int limit) {
		return productRepository.findWithPaging(excludeFirst, excludeLast, limit);
	}

	@Transactional(readOnly = true)
	public List<ProductItemDto> findWithPagingAndCategory(int excludeFirst, int excludeLast, int limit,
		int categoryId) {
		return productRepository.findWithPagingAndCategory(excludeFirst, excludeLast, limit, categoryId);
	}

	@Transactional(readOnly = true)
	public int getCount() {
		return productRepository.countAll();
	}

	@Transactional(readOnly = true)
	public int getCountByCategory(int categoryId) {
		return productRepository.countByCategory(categoryId);
	}
}
