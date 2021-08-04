package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.ProductItemDto;
import com.nts.intern.reserve.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ProductItemDto> findWithPaging(int excludeFirst, int excludeLast, int limit) {
		return productRepository.findWithPaging(excludeFirst, excludeLast, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductItemDto> findWithPagingAndCategory(int excludeFirst, int excludeLast, int limit,
		int categoryId) {
		return productRepository.findWithPagingAndCategory(excludeFirst, excludeLast, limit, categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCount() {
		return productRepository.countAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByCategory(int categoryId) {
		return productRepository.countByCategory(categoryId);
	}
}
