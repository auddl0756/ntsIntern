package com.nts.intern.reserve.repository.reserve;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void notNullTest() {
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void findById() {
		int sampleDisplayInfoId = 1;
		System.out.println(productRepository.findById(sampleDisplayInfoId));
	}
	
	@Transactional
	@Test
	public void updateCancelFlagTest() {
		int sampleReservationInfoId = 87;
		int affectedCount = productRepository.updateCancelFlag(sampleReservationInfoId);
		
		System.out.println(affectedCount);
		
		assertThat(affectedCount).isGreaterThan(0);
	}
}
