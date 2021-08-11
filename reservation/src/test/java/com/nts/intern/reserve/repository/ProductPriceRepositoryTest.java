package com.nts.intern.reserve.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductPriceRepositoryTest {
	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Test
	public void notNullTest() {
		assertThat(productPriceRepository).isNotNull();
	}

	@Test
	public void findByIdTest() {
		int sampleDisplayInfoId = 40;
		System.out.println(productPriceRepository.findById(sampleDisplayInfoId));
	}

}
