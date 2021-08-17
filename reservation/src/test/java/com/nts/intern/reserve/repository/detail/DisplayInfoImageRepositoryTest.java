package com.nts.intern.reserve.repository.detail;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.repository.detail.DisplayInfoImageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DisplayInfoImageRepositoryTest {
	@Autowired
	private DisplayInfoImageRepository displayInfoImageRepository;

	@Test
	public void notNullTest() {
		assertThat(displayInfoImageRepository).isNotNull();
	}

	@Test
	public void findByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(displayInfoImageRepository.findById(sampleDisplayInfoId));
	}

}
