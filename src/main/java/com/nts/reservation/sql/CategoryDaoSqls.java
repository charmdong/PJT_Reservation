package com.nts.reservation.sql;

/**
 * 카테고리 관련 SQL문
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT "
			+ "c.id, c.name, count(*) as count "
			+ "FROM display_info di "
			+ "JOIN product p ON di.product_id = p.id "
			+ "JOIN category c ON c.id = p.category_id "
			+ "GROUP BY c.id, c.name;";

	public static final String INSERT = "INSERT INTO category (name) VALUES(:name);";
	public static final String UPDATE_NAME = "UPDATE category SET name = :name WHERE id = :id;";
	public static final String DELETE_BY_ID = "DELETE FROM category WHERE id = :id;";
}
