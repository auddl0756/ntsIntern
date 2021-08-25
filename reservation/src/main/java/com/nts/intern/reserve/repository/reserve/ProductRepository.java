package com.nts.intern.reserve.repository.reserve;

import static com.nts.intern.reserve.repository.sql.reserve.ProductRepositorySqls.FIND_BY_ID;
import static com.nts.intern.reserve.repository.sql.reserve.ProductRepositorySqls.UPDATE_CANCEL_FLAG;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.reserve.ProductDto;

@Repository("reservationPageProductRepository")
public class ProductRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ProductDto findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(FIND_BY_ID, params, rowMapper);
	}

	public int updateCancelFlag(int reservationInfoId,String now) {
		Map<String, Object> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);
		param.put("now", now);
		return jdbc.update(UPDATE_CANCEL_FLAG, param);
	}
}
