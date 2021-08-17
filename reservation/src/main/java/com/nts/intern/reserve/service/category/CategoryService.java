package com.nts.intern.reserve.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.dto.category.CategoryDto;
import com.nts.intern.reserve.repository.category.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDto> findAll() {
		return categoryRepository.findCategoryAll();
	}
}
