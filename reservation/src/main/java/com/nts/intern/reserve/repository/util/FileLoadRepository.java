package com.nts.intern.reserve.repository.util;

import static com.nts.intern.reserve.repository.sql.util.FileLoadRepositorySqls.FIND_IMG_BY_DISPLAY_ID;
import static com.nts.intern.reserve.repository.sql.util.FileLoadRepositorySqls.FIND_IMG_BY_PRODUCT_ID;
import static com.nts.intern.reserve.repository.sql.util.FileLoadRepositorySqls.FIND_UPLOADED_IMG_BY_ID;
import static com.nts.intern.reserve.repository.sql.util.FileLoadRepositorySqls.FIND_IMG_BY_COMMENT_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.util.FileDownloadDto;

@Repository
public class FileLoadRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileDownloadDto> rowMapper = BeanPropertyRowMapper.newInstance(FileDownloadDto.class);

	public FileLoadRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public FileDownloadDto findImgByDisplayId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(FIND_IMG_BY_DISPLAY_ID, params, rowMapper);
	}

	public FileDownloadDto findImgByProductId(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);

		return jdbc.queryForObject(FIND_IMG_BY_PRODUCT_ID, params, rowMapper);
	}

	public FileDownloadDto findImgByCommentId(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);

		return jdbc.queryForObject(FIND_IMG_BY_COMMENT_ID, params, rowMapper);
	}

	public FileDownloadDto findUploadedImgById(int commentImageId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentImageId", commentImageId);

		return jdbc.queryForObject(FIND_UPLOADED_IMG_BY_ID, params, rowMapper);
	}

}
