package com.nts.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

/**
 * 예약정보 모델
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationInfo {
	private boolean cancelFlag;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private String createDate;
	private String modifyDate;
	private int productId;
	private int reservationInfoId;
	private String reservationName;
	private String reservationEmail;
	private String reservationTel;
	private String reservationDate;
	private int totalPrice;

	public ReservationInfo() {

	}

	public ReservationInfo(ReservationParam reservationParam) {
		this.displayInfoId = reservationParam.getDisplayInfoId();
		this.productId = reservationParam.getProductId();
		this.reservationName = reservationParam.getReservationName();
		this.reservationEmail = reservationParam.getReservationEmail();
		this.reservationTel = reservationParam.getReservationTelephone();
		this.reservationDate = reservationParam.getReservationYearMonthDay();
		this.createDate = this.modifyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		//this.modifyDate = this.createDate;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
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

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ReservationInfo [cancelYn=" + cancelFlag + ", displayInfo=" + displayInfo + ", displayInfoId="
				+ displayInfoId + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId="
				+ productId + ", reservationInfoId=" + reservationInfoId + ", reservationName=" + reservationName
				+ ", reservationEmail=" + reservationEmail + ", reservationTel=" + reservationTel + ", reservationDate="
				+ reservationDate + ", totalPrice=" + totalPrice + "]";
	}
}
