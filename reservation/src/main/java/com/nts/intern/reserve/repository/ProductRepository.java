package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.ProductRepositorySqls.*;

import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.ProductDto;

@Repository
public class ProductRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductDto> findWithPaging(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_WITH_PAGING, params, rowMapper);
	}

	public List<ProductDto> findWithPagingAndCategory(Integer start, Integer limit, Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);

		return jdbc.query(SELECT_WITH_PAGING_AND_CATEGORY, params, rowMapper);
	}
}
