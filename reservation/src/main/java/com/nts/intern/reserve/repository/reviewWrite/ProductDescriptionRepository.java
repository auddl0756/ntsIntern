package com.nts.intern.reserve.repository.reviewWrite;

import static com.nts.intern.reserve.repository.sql.reviewWrite.ProductDescriptionRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDescriptionRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<String> rowMapper = BeanPropertyRowMapper.newInstance(String.class);

	public ProductDescriptionRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public String findById(int reservationInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);

		return jdbc.queryForObject(FIND_BY_ID, param, rowMapper);
	}
}
