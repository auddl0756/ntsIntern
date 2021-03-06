package com.nts.intern.reserve.repository.detail;

import static com.nts.intern.reserve.repository.sql.detail.CommentRepositorySqls.FIND_ALL_BY_ID;
import static com.nts.intern.reserve.repository.sql.detail.CommentRepositorySqls.FIND_AVERAGE_BY_ID;
import static com.nts.intern.reserve.repository.sql.detail.CommentRepositorySqls.FIND_BY_ID_LIMIT;
import static com.nts.intern.reserve.repository.sql.detail.CommentRepositorySqls.GET_TOTAL_COUNT_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.detail.CommentDto;

@Repository
public class CommentRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
	private final int LIMIT = 3;

	public CommentRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CommentDto> findAllById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(FIND_ALL_BY_ID, params, rowMapper);
	}

	public List<CommentDto> findByIdLimit(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);
		params.put("limit", LIMIT);

		return jdbc.query(FIND_BY_ID_LIMIT, params, rowMapper);
	}

	public double findAverageById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		Double queryResult = jdbc.queryForObject(FIND_AVERAGE_BY_ID, params, Double.class);

		if (queryResult == null) {
			return 0;
		} else {
			return queryResult;
		}
	}

	public int getTotalCount(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		Integer queryResult = jdbc.queryForObject(GET_TOTAL_COUNT_BY_ID, params, Integer.class);

		if (queryResult == null) {
			return 0;
		} else {
			return queryResult;
		}
	}
}
