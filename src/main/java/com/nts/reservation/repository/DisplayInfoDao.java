package com.nts.reservation.repository;

import static com.nts.reservation.sql.DisplaySqls.SELECT_AVERAGE_SCORE;
import static com.nts.reservation.sql.DisplaySqls.SELECT_DISPLAY_IMAGE;
import static com.nts.reservation.sql.DisplaySqls.SELECT_DISPLAY_INFO;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayImage;
import com.nts.reservation.dto.DisplayInfo;

/**
 * 공연정보 dao
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> infoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayImage> imageRowMapper = BeanPropertyRowMapper.newInstance(DisplayImage.class);

	/**
	 * @param dataSource
	 */
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @param displayInfoId
	 * @return displayInfo
	 */
	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, params, infoRowMapper);
	}

	/**
	 * @param displayInfoId
	 * @return displayInfoImage
	 */
	public DisplayImage selectDisplayImage(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_IMAGE, params, imageRowMapper);
	}

	/**
	 * @param displayInfoId
	 * @return averageScore
	 */
	public double selectAverageScore(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		Double averageScore = jdbc.queryForObject(SELECT_AVERAGE_SCORE, params, Double.class);
		if (averageScore == null) {
			return 0;
		} else {
			return averageScore;
		}
	}
}
