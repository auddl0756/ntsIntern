package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.CategoryDto;

public interface CategoryService {
	List<CategoryDto> findAll();
}
