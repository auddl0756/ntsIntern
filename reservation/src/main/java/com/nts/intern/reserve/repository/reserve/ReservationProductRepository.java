package com.nts.intern.reserve.repository.reserve;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.nts.intern.reserve.dto.reserve.ReservationParam;

@Repository
public class ReservationProductRepository {
	private SimpleJdbcInsert insertJdbc;

	public ReservationProductRepository(DataSource dataSource) {
		this.insertJdbc = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.withTableName("reservation_info_price");
	}

	public int save(ReservationParam reservationParam) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationParam);

		int affectedRowCount = insertJdbc.execute(params);

		return affectedRowCount;
	}
}
