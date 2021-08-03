package com.nts.intern.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.service.ProductService;

@RequestMapping("/api/productImages/")
@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	private final int ALL_CATEGORY = 0;
	private final int PAGING_SIZE = 4;

	@GetMapping("{id}")
	public List<ProductDto> findByCategory(@PathVariable(name = "id") int categoryId, @RequestParam String type,
		@RequestParam int start) {
		if (type.equals("th")) {
			if (categoryId == ALL_CATEGORY) {
				return productService.findWithPaging(start, PAGING_SIZE);
			} else {
				return productService.findWithPagingAndCategory(start, PAGING_SIZE, categoryId);
			}
		} else {
			return null; //not yet developed.
		}
	}

	@GetMapping("/size")
	public int getSize() {
		return productService.getSize();
	}

	@GetMapping("/size/{id}")
	public int getSizeByCategory(@PathVariable(name = "id") int categoryId, @RequestParam String type) {
		if (type.equals("th")) {
			return productService.getSizeByCategory(categoryId);
		} else {
			return -1; //not yet developed.
		}
	}
}
