package com.nts.intern.reserve.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.PromotionDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PromotionDaoTest {
	@Autowired
	private PromotionDao promotionDao;
	
	@Test
	public void findAllTest() {
		assertThat(promotionDao).isNotNull();
		List<PromotionDto> all = promotionDao.findAll();
		all.stream().forEach(result->{
			assertThat(result.getProductImageUrl()).contains("_th_");
		});
		
	}

}
