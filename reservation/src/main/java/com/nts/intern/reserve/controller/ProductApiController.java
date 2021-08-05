package com.nts.intern.reserve.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.dto.ProductItemDto;
import com.nts.intern.reserve.dto.ProductRequestDto;
import com.nts.intern.reserve.service.ProductService;

@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	private final int ALL_CATEGORY = 0;
	private final int PAGING_SIZE = 4;

	@PostMapping("/api/products")
	public ProductDto findByCategory(@RequestBody ProductRequestDto requestDto) {
		ProductDto productDto = new ProductDto();

		int categoryId = requestDto.getCategoryId();
		Set<Integer> displayInfoIds = requestDto.getDisplayInfoIds();

		if (categoryId == ALL_CATEGORY) {
			List<ProductItemDto> items = productService.findWithPaging(displayInfoIds, PAGING_SIZE);
			int totalCount = productService.getCount();

			productDto.setItems(items);
			productDto.setTotalCount(totalCount);
		} else {
			List<ProductItemDto> items = productService.findWithPagingAndCategory(displayInfoIds,
				PAGING_SIZE, categoryId);
			int totalCount = productService.getCountByCategory(categoryId);

			productDto.setItems(items);
			productDto.setTotalCount(totalCount);
		}

		return productDto;
	}
}
