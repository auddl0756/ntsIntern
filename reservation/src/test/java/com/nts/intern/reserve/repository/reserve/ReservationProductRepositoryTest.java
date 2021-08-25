package com.nts.intern.reserve.repository.reserve;

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
public class ReservationProductRepositoryTest {
	@Autowired
	private ReservationProductRepository reservationProductRepository;

	@Test
	public void notNullTest() {
		assertThat(reservationProductRepository).isNotNull();
	}

	@Test
	@Transactional
	public void insertTest() {
		ReservationParam sampleReservationParam = new ReservationParam();

		sampleReservationParam.setProductId(1);
		sampleReservationParam.setDisplayInfoId(1);
		sampleReservationParam.setReservationName("sample name");
		sampleReservationParam.setReservationEmail("sample email");
		sampleReservationParam.setReservationTel("sample tel");
		sampleReservationParam.setReservationDate("2021.1.1");
		sampleReservationParam.setCancelFlag(false);
		sampleReservationParam.setReservationPrices(Collections.emptyList());
		sampleReservationParam.setCreateDate("2021.1.1");
		sampleReservationParam.setModifyDate("2021.1.1");

		assertThat(reservationProductRepository.saveReservationInfo(sampleReservationParam)).isGreaterThan(0);
	}
}
