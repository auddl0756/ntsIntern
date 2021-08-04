package com.nts.intern.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.CategoryDto;
import com.nts.intern.reserve.service.CategoryService;

@RestController
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/api/categories")
	public List<CategoryDto> findAllCategories() {
		return categoryService.findAll();
	}
}
