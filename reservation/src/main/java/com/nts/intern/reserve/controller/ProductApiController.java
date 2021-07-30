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

	@GetMapping("{id}")
	public List<ProductDto> findByCateogory(@PathVariable(name = "id") int categoryId, @RequestParam String type) {
		System.out.println(type);
		if (type.equals("th")) {
			if (categoryId == 0) { 
				return productService.findWithPaging(0, 4);
			} else {
				return productService.findWithPagingAndCategory(0, 4, categoryId);
			}
		} else {
			return null; //not yet developed.
		}
	}
}
