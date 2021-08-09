package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.CommentRepositorySqls.FIND_ALL_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.CommentDto;

@Repository
public class CommentRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);

	public CommentRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CommentDto> findAllById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(FIND_ALL_BY_ID, params, rowMapper);
	}
}
