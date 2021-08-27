package com.nts.intern.reserve.repository.reviewWrite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReviewWriteInitRepositoryTest {
	@Autowired
	private ReviewWriteRepository reviewWriteInitRepository;
	
	@Test
	public void notNullTest() {
		assertThat(reviewWriteInitRepository).isNotNull();
	}
	
	@Test
	public void findByIdTest() {
		int sampleReservationInfoId =1;
		System.out.println(reviewWriteInitRepository.findById(sampleReservationInfoId));
	}
}
