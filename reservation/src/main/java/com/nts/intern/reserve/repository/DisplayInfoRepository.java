package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.DisplayInfoRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.DisplayInfoDto;

@Repository
public class DisplayInfoRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);

	public DisplayInfoRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoDto findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(FIND_BY_ID, params, rowMapper);
	}
}
