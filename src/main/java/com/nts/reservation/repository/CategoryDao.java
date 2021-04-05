package com.nts.reservation.repository;

import static com.nts.reservation.sql.CategoryDaoSqls.DELETE_BY_ID;
import static com.nts.reservation.sql.CategoryDaoSqls.SELECT_ALL;
import static com.nts.reservation.sql.CategoryDaoSqls.UPDATE_NAME;

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

import com.nts.reservation.dto.Category;

/**
 * 카테고리 dao
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	/**
	 * @param dataSource
	 */
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("category").usingGeneratedKeyColumns("id");
	}

	/**
	 * @return categoryList
	 */
	public List<Category> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}

	/**
	 * @param category
	 * @return id
	 */
	public int insert(Category category) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(category);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	/**
	 * @param id
	 * @param name
	 * @return updateCount
	 */
	public int updateName(int id, String name) {
		Map<String, Object> params = new HashMap<>(2);

		params.put("id", id);
		params.put("name", name);

		return jdbc.update(UPDATE_NAME, params);
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
