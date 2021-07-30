package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> findWithPaging(int start, int limit) {
		return productRepository.findWithPaging(start, limit);
	}

	@Override
	public List<ProductDto> findWithPagingAndCategory(int start, int limit, int categoryId) {
		return productRepository.findWithPagingAndCategory(start, limit, categoryId);
	}
}
