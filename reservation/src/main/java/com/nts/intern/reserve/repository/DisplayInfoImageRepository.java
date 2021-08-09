package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.DisplayInfoImageRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.DisplayInfoImageDto;

@Repository
public class DisplayInfoImageRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImageDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);

	public DisplayInfoImageRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoImageDto findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(FIND_BY_ID, params, rowMapper);
	}
}
