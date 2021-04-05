package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.ReservationInfo;

/**
 * 예약 조회 Response 모델
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private int size;

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationInfo> reservations) {
		this.reservations = reservations;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
