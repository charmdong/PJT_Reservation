package com.nts.reservation.repository;

import static com.nts.reservation.sql.PromotionDaoSqls.DELETE_BY_ID;
import static com.nts.reservation.sql.PromotionDaoSqls.SELECT_ALL;
import static com.nts.reservation.sql.PromotionDaoSqls.UPDATE_PRODUCT_ID;

import java.util.Collections;
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

import com.nts.reservation.dto.Promotion;

/**
 * 프로모션 dao
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	/**
	 * @param dataSource
	 */
	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("promotion").usingGeneratedKeyColumns("id");
	}

	/**
	 * @return promotionList
	 */
	public List<Promotion> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}

	/**
	 * @param promotion
	 * @return insertCount
	 */
	public int insert(Promotion promotion) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(promotion);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	/**
	 * @param id
	 * @param productId
	 * @return updateCount
	 */
	public int updateProduct(int id, int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		params.put("product_id", productId);

		return jdbc.update(UPDATE_PRODUCT_ID, params);
	}

	/**
	 * @param id
	 * @return deleteCount
	 */
	public int deleteById(int id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}
}
