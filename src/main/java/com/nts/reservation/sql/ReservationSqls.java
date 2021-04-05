package com.nts.reservation.sql;

/**
 * 예약 관련 sql문
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationSqls {
	public static final String SELECT_RESERVATIONS = "SELECT "
			+ "SUM(rip.count * pp.price) as totalPrice, "
			+ "ri.product_id, "
			+ "ri.display_info_id, "
			+ "ri.id as reservationInfoId, "
			+ "ri.reservation_date, "
			+ "ri.reservation_email, "
			+ "ri.reservation_name, "
			+ "ri.reservation_tel, "
			+ "ri.cancel_flag, "
			+ "ri.create_date, "
			+ "ri.modify_date "
			+ "FROM reservation_info ri "
			+ "JOIN reservation_info_price rip ON ri.id = rip.reservation_info_id "
			+ "JOIN product_price pp ON pp.id = rip.product_price_id "
			+ "WHERE reservation_email = :reservationEmail "
			+ "GROUP BY ri.id;";
			
	
	public static final String SELECT_RESERVATION_INFO = "SELECT "
			+ "id as reservationInfoId, "
			+ "display_info_id, "
			+ "product_id, "
			+ "reservation_name, "
			+ "reservation_tel, "
			+ "reservation_email, "
			+ "DATE_FORMAT(reservation_date, '%Y-%m-%d') as reservationDate, "
			+ "cancel_flag, "
			+ "DATE_FORMAT(create_date, '%Y-%m-%dT%T') as createDate, "
			+ "DATE_FORMAT(modify_date, '%Y-%m-%dT%T') as modifyDate "
			+ "FROM reservation_info "
			+ "WHERE id = :reservationInfoId";
	
	public static final String SELECT_PRICES = "SELECT "
			+ "rip.id as reservationInfoPriceId, "
			+ "rip.product_price_id, "
			+ "rip.reservation_info_id, "
			+ "rip.count "
			+ "FROM reservation_info ri "
			+ "JOIN reservation_info_price rip ON ri.id = rip.reservation_info_id "
			+ "WHERE ri.id = :reservationInfoId";
	
	public static final String INSERT_RESERVATION_PRICE = "INSERT INTO reservation_info_price ("
			+ "reservation_info_id, "
			+ "product_price_id, "
			+ "count"
			+ ") VALUES ("
			+ ":reservationInfoId, "
			+ ":productPriceId, "
			+ ":count"
			+ ")";
	
	public static final String UPDATE_RESERVATION = "UPDATE reservation_info "
			+ "SET "
			+ "cancel_flag = true, "
			+ "modify_date = now() "
			+ "WHERE id = :reservationInfoId";
	
	public static final String INSERT_COMMENT = "";
}
