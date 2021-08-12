package com.nts.intern.reserve.repository.category;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.category.PromotionDto;
import com.nts.intern.reserve.repository.category.PromotionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PromotionRepositoryTest {
	@Autowired
	private PromotionRepository promotionRepository;

	@Test
	public void daoNotNullTest() {
		assertThat(promotionRepository).isNotNull();
	}

	@Test
	public void findAllTest() {
		List<PromotionDto> all = promotionRepository.findAll();
		all.stream().forEach(result -> {
			assertThat(result.getProductImageUrl()).contains("_th_");
		});
	}

}
