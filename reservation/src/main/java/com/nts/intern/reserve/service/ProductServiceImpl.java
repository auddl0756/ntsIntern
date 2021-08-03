package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ProductDto> findWithPaging(int start, int limit) {
		return productRepository.findWithPaging(start, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDto> findWithPagingAndCategory(int start, int limit, int categoryId) {
		return productRepository.findWithPagingAndCategory(start, limit, categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public int getSize() {
		return productRepository.countAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getSizeByCategory(int categoryId) {
		return productRepository.countByCategory(categoryId);
	}
}
