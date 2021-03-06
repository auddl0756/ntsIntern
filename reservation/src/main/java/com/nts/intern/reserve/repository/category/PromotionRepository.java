package com.nts.intern.reserve.repository.category;

import static com.nts.intern.reserve.repository.sql.category.PromotionRepositorySqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.category.PromotionDto;

@Repository
public class PromotionRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionDto> rowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);

	public PromotionRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PromotionDto> findAll() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}

}
