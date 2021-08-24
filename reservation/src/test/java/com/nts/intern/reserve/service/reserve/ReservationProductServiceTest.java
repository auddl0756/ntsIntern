package com.nts.intern.reserve.service.reserve;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.reserve.ReservationParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReservationProductServiceTest {
	@Autowired
	private ReservationProductService reservationProductService;

	@Test
	public void notNullTest() {
		assertThat(reservationProductService).isNotNull();
	}

	@Transactional
	@Test
	public void saveTest() {
		ReservationParam sampleReservationParam = new ReservationParam();

		sampleReservationParam.setProductId(1);
		sampleReservationParam.setDisplayInfoId(1);
		sampleReservationParam.setReservationName("sample name");
		sampleReservationParam.setReservationEmail("sample email");
		sampleReservationParam.setReservationTel("sample tel");
		sampleReservationParam.setReservationDate("2021.1.1");
		sampleReservationParam.setCancelFlag(false);
		sampleReservationParam.setReservationPrices(Collections.emptyList());

		int affectedRowCount = reservationProductService.save(sampleReservationParam);

		System.out.println(affectedRowCount);

		assertThat(affectedRowCount).isGreaterThan(-1);
	}

}
