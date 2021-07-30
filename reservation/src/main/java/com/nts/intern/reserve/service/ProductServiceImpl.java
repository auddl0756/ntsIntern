package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dao.ProductDao;
import com.nts.intern.reserve.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<ProductDto> findWithPaging(int start,int limit) {
		return productDao.findWithPaging(start, limit);
	}
	
	@Override
	public List<ProductDto> findWithPagingAndCategory(int start,int limit,int categoryId) {
		return productDao.findWithPagingAndCategory(start, limit, categoryId);
	}
}
