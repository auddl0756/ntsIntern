package com.nts.intern.reserve.repository.reserve;

import static com.nts.intern.reserve.repository.sql.reserve.ReservationPriceRepositorySqls.FIND_BY_ID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reserve.ReservationPriceDto;

@Repository
public class ReservationPriceRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationPriceDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationPriceDto.class);

	public ReservationPriceRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationPriceDto> findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(FIND_BY_ID, params, rowMapper);
	}
}
