package com.nts.reservation.sql;

/**
 * 상품 관련 SQL문
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
public class ProductDaoSqls {
	public static final String WHERE = "WHERE ";
	public static final String AND = "AND ";
	public static final String COMPARE_CATEGORYID = "p.category_id = :categoryId ";
	public static final String LIMIT = "LIMIT :start ,:limit;";
	
	public static final String SELECT_PRODUCT_INFO = "SELECT "
			+ "di.id as displayInfoId, "
			+ "di.place_name as placeName, "
			+ "p.content, "
			+ "p.description, "
			+ "p.id, "
			+ "fi.save_file_name as productImageUrl "
			+ "FROM product p "
			+ "JOIN display_info di ON di.product_id = p.id "
			+ "JOIN product_image pi ON pi.product_id = p.id AND pi.type = 'th' "
			+ "JOIN file_info fi ON fi.id = pi.file_id ";
	public static final String SELECT_DISPLAY_COUNT = "SELECT "
			+ "count(*) as count "
			+ "FROM display_info di "
			+ "JOIN product p ON di.product_id = p.id ";

	public static final String INSERT = "INSERT INTO product ("
			+ "category_id, "
			+ "description, "
			+ "content, "
			+ "event, "
			+ "create_date, "
			+ "modify_date"
			+ ") "
			+ "VALUES (:category_id, :description, :content, :event, now(), now());";
	public static final String UPDATE = "UPDATE product "
			+ "SET "
			+ "category_id = :categoryId, "
			+ "description = :description, "
			+ "content = content, "
			+ "event = event, "
			+ "modifyDate = now() "
			+ "WHERE id = :id;";
	public static final String DELETE_BY_ID = "DELETE FROM product WHERE id = :id;";
}
