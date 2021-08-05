package com.nts.intern.reserve.service;

import java.util.List;
import java.util.Set;

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
	public List<ProductItemDto> findWithPaging(Set<Integer> displayInfoIds, int limit) {
		return productRepository.findWithPaging(displayInfoIds, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductItemDto> findWithPagingAndCategory(Set<Integer> displayInfoIds, int limit,
		int categoryId) {
		return productRepository.findWithPagingAndCategory(displayInfoIds, limit, categoryId);
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
