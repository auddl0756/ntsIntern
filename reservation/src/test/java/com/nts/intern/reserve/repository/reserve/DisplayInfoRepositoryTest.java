package com.nts.intern.reserve.repository.reserve;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DisplayInfoRepositoryTest {
	@Autowired
	private DisplayInfoRepository displayInfoRepository;

	@Test
	public void notNullTest() {
		assertThat(displayInfoRepository).isNotNull();
	}

	@Test
	public void displayInfo조회Test() {
		int sampleDisplayInfoId = 1;
		System.out.println(displayInfoRepository.findById(sampleDisplayInfoId));
		assertThat(displayInfoRepository.findById(sampleDisplayInfoId).getDisplayInfoId())
			.isEqualTo(sampleDisplayInfoId);
	}
}
