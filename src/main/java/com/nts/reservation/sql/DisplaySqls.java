package com.nts.reservation.sql;

/**
 * 공연정보 관련 sql문
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
public class DisplaySqls { 
	public static final String LIMIT = "LIMIT 0, :limit";
	public static final String SELECT_AVERAGE_SCORE = "SELECT "
			+ "avg(c.score) as averageScore " 
			+ "FROM display_info di " 
			+ "JOIN reservation_user_comment c ON c.product_id = di.product_id " 
			+ "WHERE di.id = :displayInfoId";
	
	public static final String SELECT_COMMENTS = "SELECT "
			+ "c.id as commentId, "
			+ "c.comment, "
			+ "c.score, "
			+ "c.product_id, "
			+ "DATE_FORMAT(c.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(c.modify_date,'%Y-%m-%dT%TZ') as modifyDate, "
			+ "c.reservation_info_id, "
			+ "ri.reservation_name, "
			+ "ri.reservation_email, "
			+ "ri.reservation_tel as reservationTelephone, "
			+ "DATE_FORMAT(ri.reservation_date,'%Y-%m-%dT%TZ') as reservationDate "
			+ "FROM display_info di "
			+ "JOIN reservation_info ri ON ri.display_info_id = di.id "
			+ "JOIN reservation_user_comment c ON c.reservation_info_id = ri.id "
			+ "WHERE di.id = :displayInfoId "
			+ "ORDER BY c.create_date ";
	
	public static final String SELECT_COMMENT = "SELECT "
			+ "comment, "
			+ "id as commentId, "
			+ "create_date, "
			+ "modify_date, "
			+ "product_id, "
			+ "reservation_info_id, "
			+ "score "
			+ "FROM reservation_user_comment "
			+ "WHERE id = :commentId ";
	
	public static final String SELECT_COMMENT_COUNT = "SELECT "
			+ "count(*) as commentCount "
			+ "FROM display_info di "
			+ "JOIN reservation_info ri ON ri.display_info_id = di.id "
			+ "JOIN reservation_user_comment c ON c.reservation_info_id = ri.id "
			+ "WHERE di.id = :displayInfoId "; 
	
	public static final String SELECT_COMMENT_IMAGES = "SELECT "
			+ "fi.id as fileId, "
			+ "fi.content_type, "
			+ "fi.file_name, "
			+ "fi.save_file_name, "
			+ "fi.delete_flag, "
			+ "DATE_FORMAT(fi.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(fi.modify_date,'%Y-%m-%dT%TZ') as modifyDate, "
			+ "cimg.id as imageId, "
			+ "cimg.reservation_info_id, "
			+ "cimg.reservation_user_comment_id "
			+ "FROM display_info di "
			+ "JOIN reservation_info ri ON ri.display_info_id = di.id "
			+ "JOIN reservation_user_comment c ON c.reservation_info_id = ri.id "
			+ "JOIN reservation_user_comment_image cimg ON cimg.reservation_user_comment_id = c.id "
			+ "JOIN file_info fi ON cimg.file_id = fi.id "
			+ "WHERE c.id = :commentId";
	
	public static final String SELECT_COMMENT_IMAGE = "SELECT "
			+ "fi.content_type, "
			+ "fi.create_date, "
			+ "fi.modify_date, "
			+ "fi.delete_flag, "
			+ "fi.file_name, "
			+ "fi.save_file_name, "
			+ "cimg.file_id, "
			+ "cimg.id as imageId, "
			+ "cimg.reservation_info_id, "
			+ "cimg.reservation_user_comment_id "
			+ "FROM reservation_user_comment_image cimg "
			+ "JOIN file_info fi ON cimg.file_id = fi.id "
			+ "WHERE cimg.reservation_user_comment_id = :reservationUserCommentId";
	
	public static final String SELECT_COMMENT_IMAGE_BY_ID = "SELECT "
			+ "fi.content_type, "
			+ "fi.file_name, "
			+ "fi.save_file_name, "
			+ "FROM file_info fi "
			+ "JOIN reservation_user_comment_image cimg ON cimg.file_id = fi.id "
			+ "WHERE cimg.id = :imageId";
	
	public static final String SELECT_DISPLAY_INFO = "SELECT "
			+ "p.content as productContent, "
			+ "p.description as productDescription, "
			+ "p.event as productEvent, "
			+ "c.id as categoryId, "
			+ "c.name as categoryName, " 
			+ "di.id as displayInfoId, "
			+ "di.email, "
			+ "di.homepage, "
			+ "di.opening_hours, "
			+ "di.place_lot, "
			+ "di.place_name, "
			+ "di.place_street, "
			+ "di.product_id, "
			+ "di.tel as telephone, " 
			+ "DATE_FORMAT(di.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(di.modify_date,'%Y-%m-%dT%TZ') as modifyDate "
			+ "FROM product p " 
			+ "JOIN display_info di ON di.product_id = p.id " 
			+ "JOIN category c ON c.id = p.category_id " 
			+ "WHERE di.id = :displayInfoId";
	
	public static final String SELECT_DISPLAY_IMAGE = "SELECT "
			+ "dimg.file_id, "
			+ "dimg.display_info_id, "
			+ "dimg.id as displayInfoImageId, "
			+ "fi.content_type, "
			+ "fi.delete_flag, "
			+ "fi.file_name, "
			+ "fi.save_file_name, " 
			+ "DATE_FORMAT(fi.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(fi.modify_date,'%Y-%m-%dT%TZ') as modifyDate "
			+ "FROM display_info di " 
			+ "JOIN display_info_image dimg ON di.id = dimg.display_info_id " 
			+ "JOIN file_info fi ON fi.id = dimg.file_id " 
			+ "WHERE di.id = :displayInfoId";
	
	public static final String SELECT_PRODUCT_IMAGES = "SELECT "
			+ "fi.id as fileInfoId, "
			+ "fi.content_type, "
			+ "fi.delete_flag, "
			+ "fi.file_name, "
			+ "fi.save_file_name, "
			+ "DATE_FORMAT(fi.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(fi.modify_date,'%Y-%m-%dT%TZ') as modifyDate, " 
			+ "pimg.product_id, "
			+ "pimg.id as productImageId, "
			+ "pimg.type " 
			+ "FROM product p " 
			+ "JOIN display_info di ON di.product_id = p.id " 
			+ "JOIN product_image pimg ON pimg.product_id = p.id " 
			+ "JOIN file_info fi ON fi.id = pimg.file_id " 
			+ "WHERE di.id = :displayInfoId "
			+ "GROUP BY pimg.type";
			
	public static final String SELECT_PRODUCT_PRICE = "SELECT "
			+ "price.product_id as productId, "
			+ "price.id as productPriceId, "
			+ "price.price, "
			+ "price.price_type_name, "
			+ "price.discount_rate, "
			+ "DATE_FORMAT(price.create_date,'%Y-%m-%dT%TZ') as createDate, "
			+ "DATE_FORMAT(price.modify_date,'%Y-%m-%dT%TZ') as modifyDate "
			+ "FROM product p " 
			+ "JOIN display_info di ON p.id = di.product_id " 
			+ "JOIN product_price price ON p.id = price.product_id " 
			+ "WHERE di.id = :displayInfoId";
}
