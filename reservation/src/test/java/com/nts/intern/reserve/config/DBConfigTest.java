package com.nts.intern.reserve.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DBConfigTest {
	@Autowired
	BasicDataSource dataSource;

	@Test
	public void dataSourceNotNullTest() {
		assertThat(dataSource).isNotNull();
	}

	@Test
	public void DBConnectionTest() {
		try {
			assertThat(dataSource.getConnection()).isNotNull();
			assertThat(dataSource.getUsername()).isEqualTo("user07");
		} catch (SQLException sqlException) {
			System.err.println(sqlException.getMessage());
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
