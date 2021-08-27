package com.nts.intern.reserve.repository.reviewWrite;

import static com.nts.intern.reserve.repository.sql.reviewWrite.ReviewWriteRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reviewWrite.ReviewWriteInitDto;

@Repository
public class ReviewWriteRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReviewWriteInitDto> rowMapper = BeanPropertyRowMapper.newInstance(ReviewWriteInitDto.class);

	public ReviewWriteRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReviewWriteInitDto findById(int reservationInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);

		return jdbc.queryForObject(FIND_BY_ID, param, rowMapper);
	}
}
