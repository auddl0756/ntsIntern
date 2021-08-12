package com.nts.intern.reserve.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.intern.reserve.dto.category.ProductDto;
import com.nts.intern.reserve.dto.category.ProductItemDto;
import com.nts.intern.reserve.service.category.ProductService;

@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	private final int ALL_CATEGORY = 0;
	private final int PAGING_SIZE = 4;

	@GetMapping("/api/products")
	public ProductDto findByCategory(
		@RequestParam(required = false, defaultValue = "0") int excludeFirst,
		@RequestParam(required = false, defaultValue = "0") int excludeLast,
		@RequestParam int categoryId) {

		ProductDto productDto = new ProductDto();

		if (categoryId == ALL_CATEGORY) {
			List<ProductItemDto> items = productService.findWithPaging(excludeFirst, excludeLast, PAGING_SIZE);
			int totalCount = productService.getCount();

			productDto.setItems(items);
			productDto.setTotalCount(totalCount);
		} else {
			List<ProductItemDto> items = productService.findWithPagingAndCategory(excludeFirst, excludeLast,
				PAGING_SIZE, categoryId);
			int totalCount = productService.getCountByCategory(categoryId);

			productDto.setItems(items);
			productDto.setTotalCount(totalCount);
		}

		return productDto;
	}
}
