package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.CommentImageRepositorySqls.FIND_ALL_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.CommentImageDto;

@Repository
public class CommentImageRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImageDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);

	public CommentImageRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CommentImageDto> findAllById(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);

		return jdbc.query(FIND_ALL_BY_ID, params, rowMapper);
	}
}
