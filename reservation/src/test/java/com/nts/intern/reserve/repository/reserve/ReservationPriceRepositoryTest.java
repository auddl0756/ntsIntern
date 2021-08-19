package com.nts.intern.reserve.repository.reserve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReservationPriceRepositoryTest {
	@Autowired
	private ReservationPriceRepository reservationPriceRepository;

	@Test
	public void notNulltest() {
		assertThat(reservationPriceRepository).isNotNull();
	}

	@Test
	public void findByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(reservationPriceRepository.findById(sampleDisplayInfoId));
	}

	@Test
	public void findMinPriceTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(reservationPriceRepository.findMinPrice(sampleDisplayInfoId));
	}

}
