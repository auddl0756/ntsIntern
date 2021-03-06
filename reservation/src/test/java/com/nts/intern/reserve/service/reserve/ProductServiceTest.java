package com.nts.intern.reserve.service.reserve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductServiceTest {
	@Autowired
	private ProductService productService;

	@Test
	public void NotNullTest() {
		assertThat(productService).isNotNull();
	}

	@Test
	public void findById() {
		System.out.println(productService.findById(1));
	}

	@Test
	public void 랜덤_예매일_생성Test() {
		String randomReservationDate = productService.makeRandomReservationDate();
		System.out.println(randomReservationDate);
	}
}
