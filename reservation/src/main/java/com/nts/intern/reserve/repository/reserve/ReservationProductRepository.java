package com.nts.intern.reserve.repository.reserve;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reserve.ReservationParam;
import com.nts.intern.reserve.dto.reserve.ReservationPrice;

@Repository
public class ReservationProductRepository {
	private SimpleJdbcInsert jdbcInsertReservationInfo;
	private SimpleJdbcInsert jdbcInsertReservationInfoPrice;
	private NamedParameterJdbcTemplate jdbc;

	public ReservationProductRepository(DataSource dataSource) {
		this.jdbcInsertReservationInfo = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");

		this.jdbcInsertReservationInfoPrice = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price");

		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int saveReservationInfo(ReservationParam reservationParam) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationParam);

		int newestReservationInfoId = jdbcInsertReservationInfo.executeAndReturnKey(params).intValue();

		return newestReservationInfoId;
	}

	public int saveReservationInfoPrice(ReservationPrice price) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(price);

		return jdbcInsertReservationInfoPrice.execute(params);
	}
}
