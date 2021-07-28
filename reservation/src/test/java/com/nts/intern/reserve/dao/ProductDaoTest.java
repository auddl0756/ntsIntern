package com.nts.intern.reserve.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.ProductDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductDaoTest {
	@Autowired
	private ProductDao productDao;

	@Test
	public void selectAllTest() {
		assertThat(productDao).isNotNull();
		List<ProductDto> list = productDao.findAll(0, 5);

		list.stream().forEach(product -> {
			assertThat(product.getContent()).isNotNull();
		});
	}

	@Test
	public void selectCountTest() {
		assertThat(productDao).isNotNull();
		assertThat(productDao.getCount()).isGreaterThan(-1);
		System.out.println(productDao.getCount());
	}
}
