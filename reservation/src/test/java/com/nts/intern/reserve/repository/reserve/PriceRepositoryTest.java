package com.nts.intern.reserve.repository.reserve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PriceRepositoryTest {
	
	@Autowired
	//@Qualifier("reservationPageProductPrice")	//@Qualifier가 없어도 정상적으로 동작.
	private PriceRepository priceRepository;

	@Test
	public void notNullTest() {
		assertThat(priceRepository).isNotNull();
	}
	
	@Test
	public void 올바른_빈을_주입하는지_Test() {
		System.out.println(priceRepository);
		assertThat(priceRepository.toString().contains("reserve.*.reserve"));
	}

	@Test
	public void findByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(priceRepository.findById(sampleDisplayInfoId));
	}
}
