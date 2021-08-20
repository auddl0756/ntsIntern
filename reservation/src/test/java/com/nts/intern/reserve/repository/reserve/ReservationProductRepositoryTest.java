package com.nts.intern.reserve.repository.reserve;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReservationProductRepositoryTest {

	@Autowired
	private ReservationProductRepository reservationProductRepository;

	@Test
	public void notNullTest() {
		assertThat(reservationProductRepository).isNotNull();
	}

	@Test
	public void findById() {
		int sampleDisplayInfoId = 1;
		System.out.println(reservationProductRepository.findById(sampleDisplayInfoId));
	}
}
