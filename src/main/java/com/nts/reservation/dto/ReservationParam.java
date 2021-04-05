package com.nts.reservation.dto;

import java.util.List;

/**
 * 예약하기 Request Body 모델
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationParam {
	private int displayInfoId;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationName;
	private String reservationEmail;
	private String reservationTelephone;
	private String reservationYearMonthDay;
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	@Override
	public String toString() {
		return "ReservationParam [displayInfoId=" + displayInfoId + ", prices=" + prices + ", productId=" + productId
				+ ", reservationName=" + reservationName + ", reservationEmail=" + reservationEmail
				+ ", reservationTelephone=" + reservationTelephone + ", reservationYearMonthDay="
				+ reservationYearMonthDay + "]";
	}

}
