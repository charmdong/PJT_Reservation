package com.nts.reservation.sql;

/**
 * 프로모션 관련 SQL문
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT "
			+ "pro.id, "
			+ "pro.product_id, "
			+ "fi.save_file_name as productImageUrl " 
			+ "FROM product_image pi " 
			+ "JOIN promotion pro ON pi.product_id = pro.id AND pi.type = 'th' "
			+ "JOIN file_info fi ON pi.file_id = fi.id;";

	public static final String INSERT = "INSERT INTO promotion (product_id) VALUES(:product_id);";
	public static final String UPDATE_PRODUCT_ID = "UPDATE promotion SET product_id = :product_id WHERE id= :id;";
	public static final String DELETE_BY_ID = "DELETE FROM promotion WHERE id = :id;";
}
