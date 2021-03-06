package com.nts.intern.reserve.repository.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.category.ProductDto;
import com.nts.intern.reserve.dto.category.ProductItemDto;
import com.nts.intern.reserve.repository.category.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void daoNotNullTest() {
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void findWithPagingAndCategoryTest() {
		List<ProductItemDto> list = productRepository.findWithPagingAndCategory(0, 0, 4, 1);
		list.stream().forEach(product -> {
			assertThat(product.getProductContent()).isNotNull();
		});
	}

	@Test
	public void findWithPagingTest() {
		List<ProductItemDto> list = productRepository.findWithPaging(0, 0, 4);
		list.stream().forEach(product -> {
			assertThat(product.getProductContent()).isNotNull();
		});
	}

	@Test
	public void countAllTest() {
		int countAll = productRepository.countAll();
		assertThat(countAll).isGreaterThan(-1);
	}

	@Test
	public void countWithCateogoryTest() {
		int total = productRepository.countAll();
		int sum = 0;
		for (int id = 1; id <= 5; id++) {
			sum += productRepository.countByCategory(id);
		}

		assertThat(total).isEqualTo(sum);
	}

}
