package com.nts.intern.reserve.controller.reserve;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.config.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class, ApplicationConfig.class})
public class ProductReservationApiControllerTest {
	@Autowired
	private ProductReservationRestController productReservationRestController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(productReservationRestController).build();
	}

	@Test
	public void notNullTest() {
		assertThat(mockMvc).isNotNull();
		assertThat(productReservationRestController).isNotNull();
	}

	@Transactional
	@Test
	public void requestTest() {
		try {
			mockMvc.perform(post("/api/reservations/")
				.param("name", "name")
				.param("email", "email")
				.param("tel", "tel")
				.param("form_product_id", "1")
				.param("form_display_info_id", "1")
				.param("form_date", "2021.1.1")
				.param("form_prices",
					"[{\"productPriceId\":\"1\",\"count\":\"2\"},{\"productPriceId\":\"2\",\"count\":\"3\"},{\"productPriceId\":\"3\",\"count\":\"1\"}]")

				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
