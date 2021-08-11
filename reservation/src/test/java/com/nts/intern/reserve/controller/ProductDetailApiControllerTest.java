package com.nts.intern.reserve.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.config.WebMvcConfig;

// reference : https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class, ApplicationConfig.class})
public class ProductDetailApiControllerTest {
	@Autowired
	private ProductDetailApiController productDetailApiController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(productDetailApiController).build();
	}

	@Test
	public void notNullTest() {
		assertThat(mockMvc).isNotNull();
		assertThat(productDetailApiController).isNotNull();
	}

	@Test
	public void requestTest() {
		try {
			int sampleDisplayInfoId = 1;
			MvcResult response = mockMvc.perform(get("/api/products/"+sampleDisplayInfoId))
				.andReturn();

			System.out.println(response.getResponse().getContentAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
