package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.CategoryDto;
import com.nts.intern.reserve.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository CategoryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> findAll() {
		return CategoryRepository.findCategoryAll();
	}
}
