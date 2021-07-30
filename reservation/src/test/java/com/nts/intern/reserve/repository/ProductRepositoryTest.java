package com.nts.intern.reserve.repository;

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
import com.nts.intern.reserve.dto.ProductDto;
import com.nts.intern.reserve.repository.ProductRepository;

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
		List<ProductDto> list = productRepository.findWithPagingAndCategory(0, 5, 1);
		list.stream().forEach(product -> {
			assertThat(product.getProductContent()).isNotNull();
		});
	}

	@Test
	public void findWithPagingTest() {
		List<ProductDto> list = productRepository.findWithPaging(0, 5);
		list.stream().forEach(product -> {
			assertThat(product.getProductContent()).isNotNull();
		});
	}
}
