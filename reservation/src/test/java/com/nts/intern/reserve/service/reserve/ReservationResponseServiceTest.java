package com.nts.intern.reserve.service.reserve;

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
public class ReservationResponseServiceTest {
	@Autowired
	private ReservationResponseService reservationResponseService;

	@Test
	public void notNullTest() {
		assertThat(reservationResponseService).isNotNull();
	}

	@Test
	public void findAllReservationsByEmailTest() {
		String sampleEmail = "leemr@naver.com";

		List<ReservationResponseDto> result = reservationResponseService.findAllReservationsByEmail(sampleEmail);
		System.out.println(result);

		ReservationResponseDto first = result.get(0);

		assertThat(first.getDisplayInfo()).isNotNull();
		assertThat(first.getTotalPrice()).isGreaterThan(0);
	}
}
