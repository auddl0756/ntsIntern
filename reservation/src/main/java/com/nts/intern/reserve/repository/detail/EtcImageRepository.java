package com.nts.intern.reserve.repository.detail;

import static com.nts.intern.reserve.repository.sql.detail.EtcImageRepositorySqls.FIND_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.intern.reserve.dto.detail.EtcImageDto;

@Repository
public class EtcImageRepository {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<EtcImageDto> rowMapper = BeanPropertyRowMapper.newInstance(EtcImageDto.class);

	public EtcImageRepository(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<EtcImageDto> findById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(FIND_BY_ID, params, rowMapper);
	}
}
