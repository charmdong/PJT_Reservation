package com.nts.reservation.dto;

/**
 * 예약 가격 내역
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationPrice {
	private int productPriceId;
	private int count;
	private int reservationInfoId;
	private int reservationInfoPriceId;

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(int reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "ReservationPrice [productPriceId=" + productPriceId + ", count=" + count + ", reservationInfoId="
				+ reservationInfoId + ", reservationInfoPriceId=" + reservationInfoPriceId + "]";
	}

}
