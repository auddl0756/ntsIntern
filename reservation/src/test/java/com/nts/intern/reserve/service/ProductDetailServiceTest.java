package com.nts.intern.reserve.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductDetailServiceTest {
	@Autowired
	private ProductDetailService productDetailService;

	@Test
	public void notNullTest() {
		assertThat(productDetailService).isNotNull();
	}

	@Test
	public void findAllcommentsByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(productDetailService.findAllCommentsById(sampleDisplayInfoId));
	}

	@Test
	public void findDisplayInfoByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(productDetailService.findDisplayInfoById(sampleDisplayInfoId));
		;
	}

	@Test
	public void findDisplayInfoImageTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(productDetailService.findDisplayInfoImageById(sampleDisplayInfoId));
	}

	@Test
	public void findAllPricesByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(productDetailService.findAllPricesById(sampleDisplayInfoId));
	}

	@Test
	public void findAverageScoreTest() {
		IntStream.rangeClosed(1, 10).forEach(sampleDisplayInfoId -> {
			System.out.println(productDetailService.findAverageScore(sampleDisplayInfoId));
		});
	}
}
