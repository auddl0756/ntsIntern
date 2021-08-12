package com.nts.intern.reserve.repository.category;

import static com.nts.intern.reserve.repository.sql.category.CategoryRepositorySqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.category.CategoryDto;
import com.nts.intern.reserve.dto.category.ProductItemDto;

@Repository
public class CategoryRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CategoryDto> findCategoryAll() {
		return jdbc.query(SELECT_ALL_CATEGORIES, Collections.emptyMap(), rowMapper);
	}
}
