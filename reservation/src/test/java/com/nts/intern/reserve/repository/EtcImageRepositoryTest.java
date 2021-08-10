package com.nts.intern.reserve.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class EtcImageRepositoryTest {
	@Autowired
	private EtcImageRepository etcImageRepository;

	@Test
	public void notNullTest() {
		assertThat(etcImageRepository).isNotNull();
	}

	@Test
	public void findByIdTest() {
		System.out.println(etcImageRepository.findById(2));
	}
}
