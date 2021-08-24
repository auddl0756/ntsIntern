package com.nts.intern.reserve.repository.reserve;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReservationResponseRepositoryTest {
	@Autowired
	private ReservationResponseRepository reservationResponseRepository;

	@Test
	public void notNullTest() {
		assertThat(reservationResponseRepository).isNotNull();
	}

	@Test
	public void findAllReservationsByEmailTest() {
		String sampleEmail = "leemr@naver.com";

		List<ReservationResponseDto> result = reservationResponseRepository.findAllReservationsByEmail(sampleEmail);
		System.out.println(result);

		assertThat(result.size()).isGreaterThan(0);

		assertThat(result.get(0).getReservationEmail()).isEqualTo(sampleEmail);
		
		assertThat(result.get(0).getTotalPrice()).isGreaterThan(0);
		
	}
}
