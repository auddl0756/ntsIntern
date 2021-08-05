package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.ProductRepositorySqls.COUNT_ALL;
import static com.nts.intern.reserve.repository.sql.ProductRepositorySqls.COUNT_BY_CATEGORY;
import static com.nts.intern.reserve.repository.sql.ProductRepositorySqls.SELECT_WITH_PAGING;
import static com.nts.intern.reserve.repository.sql.ProductRepositorySqls.SELECT_WITH_PAGING_AND_CATEGORY;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.ProductItemDto;

@Repository
public class ProductRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductItemDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductItemDto.class);

	public ProductRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductItemDto> findWithPaging(int excludeFirst, int excludeLast, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("excludeFirst", excludeFirst);
		params.put("excludeLast", excludeLast);
		params.put("limit", limit);

		return jdbc.query(SELECT_WITH_PAGING, params, rowMapper);
	}

	public List<ProductItemDto> findWithPagingAndCategory(int excludeFirst, int excludeLast, int limit,
		int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("excludeFirst", excludeFirst);
		params.put("excludeLast", excludeLast);
		params.put("limit", limit);
		params.put("categoryId", categoryId);

		return jdbc.query(SELECT_WITH_PAGING_AND_CATEGORY, params, rowMapper);
	}

	public int countAll() {
		return jdbc.queryForObject(COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public int countByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(COUNT_BY_CATEGORY, params, Integer.class);
	}

}
