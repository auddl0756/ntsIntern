package com.nts.intern.reserve.repository;

import static com.nts.intern.reserve.repository.sql.ProductPriceRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.ProductPriceDto;

@Repository
public class ProductPriceRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPriceDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDto.class);

	public ProductPriceRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductPriceDto> findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(FIND_BY_ID, params, rowMapper);
	}
}
