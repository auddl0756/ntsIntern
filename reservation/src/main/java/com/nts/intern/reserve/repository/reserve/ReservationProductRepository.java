package com.nts.intern.reserve.repository.reserve;

import static com.nts.intern.reserve.repository.sql.reserve.ReservationProductRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reserve.ReservationProductDto;

@Repository
public class ReservationProductRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationProductDto.class);

	public ReservationProductRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReservationProductDto findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("limit", 1);

		return jdbc.queryForObject(FIND_BY_ID, params, rowMapper);
	}
}
