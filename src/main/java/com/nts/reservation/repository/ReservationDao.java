package com.nts.reservation.repository;

import static com.nts.reservation.sql.ReservationSqls.SELECT_PRICES;
import static com.nts.reservation.sql.ReservationSqls.SELECT_RESERVATIONS;
import static com.nts.reservation.sql.ReservationSqls.SELECT_RESERVATION_INFO;
import static com.nts.reservation.sql.ReservationSqls.UPDATE_RESERVATION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPrice;

/**
 * 예약 dao
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert reservationInfoInsertion;
	private SimpleJdbcInsert reservationPriceInsertion;
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper
			.newInstance(ReservationInfo.class);
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper
			.newInstance(ReservationPrice.class);

	/**
	 * @param dataSource
	 */
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.reservationInfoInsertion = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
		this.reservationPriceInsertion = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
	}

	/**
	 * @param reservationEmail
	 * @return reservationInfoList
	 */
	public List<ReservationInfo> selectReservationInfoList(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATIONS, params, reservationInfoRowMapper);
	}

	/**
	 * @param reservationInfoId
	 * @return reservationInfo
	 */
	public ReservationInfo selectReservationInfo(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_RESERVATION_INFO, params, reservationInfoRowMapper);

	}

	/**
	 * @param reservationInfoId
	 * @return reservationPriceList
	 */
	public List<ReservationPrice> selectReservationPriceList(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.query(SELECT_PRICES, params, reservationPriceRowMapper); 
	}

	/**
	 * @param reservationInfo
	 * @return reservationInfoId
	 */
	public int insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return reservationInfoInsertion.executeAndReturnKey(params).intValue();
	}

	/**
	 * @param reservationPrice
	 * @return reservationPriceId
	 */
	public int insertReservationPrice(ReservationPrice reservationPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
		return reservationPriceInsertion.executeAndReturnKey(params).intValue();
	}

	/**
	 * @param reservationInfoId
	 * @return updateCount
	 */
	public int updateReservation(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.update(UPDATE_RESERVATION, params);
	}

}
