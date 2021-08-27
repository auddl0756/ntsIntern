package com.nts.intern.reserve.repository.reviewWrite;

import static com.nts.intern.reserve.repository.sql.reviewWrite.ReviewWriteRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reviewWrite.ReviewSaveDto;
import com.nts.intern.reserve.dto.reviewWrite.ReviewWriteInitDto;

@Repository
public class ReviewWriteRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReviewWriteInitDto> rowMapper = BeanPropertyRowMapper.newInstance(ReviewWriteInitDto.class);
	private SimpleJdbcInsert jdbcInsertComment;
	private SimpleJdbcInsert jdbcInsertFileInfo;
	private SimpleJdbcInsert jdbcInsertCommentImage;

	public ReviewWriteRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

		this.jdbcInsertComment = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment")
			.usingGeneratedKeyColumns("id");

		this.jdbcInsertFileInfo = new SimpleJdbcInsert(dataSource)
			.withTableName("file_info")
			.usingGeneratedKeyColumns("id");

		this.jdbcInsertCommentImage = new SimpleJdbcInsert(dataSource)
			.usingGeneratedKeyColumns("id")
			.withTableName("reservation_user_comment_image");
	}

	public ReviewWriteInitDto findById(int reservationInfoId) {
		Map<String, Integer> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);

		return jdbc.queryForObject(FIND_BY_ID, param, rowMapper);
	}

	public void save(ReviewSaveDto dto) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(dto);

		int reservationUserCommentId = jdbcInsertComment.executeAndReturnKey(params).intValue();
		int fileId = jdbcInsertFileInfo.executeAndReturnKey(params).intValue();

		Map<String, Object> imageParams = new HashMap<>();

		imageParams.put("reservation_info_id", dto.getReservationInfoId());
		imageParams.put("reservation_user_comment_id", reservationUserCommentId);
		imageParams.put("file_id", fileId);

		jdbcInsertCommentImage.execute(imageParams);
	}
}
