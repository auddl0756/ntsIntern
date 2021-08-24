package com.nts.intern.reserve.repository.reserve;

import static com.nts.intern.reserve.repository.sql.reserve.ReservationResponseRepositorySqls.SELECT_ALL_RESERVATIONS;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reserve.ReservationResponseDto;

@Repository("reservationPageResponseRepository")
public class ReservationResponseRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationResponseDto> rowMapper = BeanPropertyRowMapper
		.newInstance(ReservationResponseDto.class);

	public ReservationResponseRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationResponseDto> findAllReservationsByEmail(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("email", email);

		return jdbc.query(SELECT_ALL_RESERVATIONS, params, rowMapper);
	}
}
