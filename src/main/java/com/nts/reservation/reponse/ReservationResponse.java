package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPrice;

/**
 * 예약하기 Response 모델
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationResponse {
	private int displayInfoId;
	private int productId;
	private List<ReservationPrice> prices;
	private int reservationInfoId;
	private String reservationName;
	private String reservationEmail;
	private String reservationTelephone;
	private String reservationDate;
	private boolean cancelYn;
	private String createDate;
	private String modifyDte;

	public ReservationResponse (ReservationInfo reservationInfo, List<ReservationPrice> priceList) {
		this.displayInfoId = reservationInfo.getDisplayInfoId();
		this.productId = reservationInfo.getProductId();
		this.reservationInfoId = reservationInfo.getReservationInfoId();
		this.reservationName = reservationInfo.getReservationName();
		this.reservationEmail = reservationInfo.getReservationEmail();
		this.reservationTelephone = reservationInfo.getReservationTel();
		this.reservationDate = reservationInfo.getReservationDate();
		this.cancelYn = reservationInfo.isCancelFlag();
		this.createDate = reservationInfo.getCreateDate();
		this.modifyDte = reservationInfo.getModifyDate();
		this.prices = priceList;
	}
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
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

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDte() {
		return modifyDte;
	}

	public void setModifyDte(String modifyDte) {
		this.modifyDte = modifyDte;
	}

}
