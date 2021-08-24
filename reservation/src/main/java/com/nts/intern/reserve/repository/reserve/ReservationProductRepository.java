package com.nts.intern.reserve.repository.reserve;

import static com.nts.intern.reserve.repository.sql.reserve.ReservationProductRepositorySqls.SELECT_LATEST_RESERVATION_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
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
			.withTableName("reservation_info");

		this.jdbcInsertReservationInfoPrice = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price");

		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	int getInsertedReservationId() {
		return jdbc.queryForObject(SELECT_LATEST_RESERVATION_ID, Collections.emptyMap(), Integer.class);
	}

	public int saveReservationInfo(ReservationParam reservationParam) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationParam);

		int affectedRowCount = jdbcInsertReservationInfo.execute(params);

		int newestReservationInfoId = getInsertedReservationId();

		for (ReservationPrice price : reservationParam.getReservationPrices()) {
			price.setReservationInfoId(newestReservationInfoId);
			saveReservationInfoPrice(price);
		}

		return affectedRowCount;
	}

	int saveReservationInfoPrice(ReservationPrice price) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(price);

		return jdbcInsertReservationInfoPrice.execute(params);
	}
}
