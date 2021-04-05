package com.nts.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.reponse.CommentResponse;

/**
 * 예약 정보 service
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public interface ReservationService {
	List<ReservationInfo> getReservationInfos(String reservationEmail);

	int insertReservation(ReservationParam reservationParam);

	ReservationInfo getReservationInfo(int reservationInfoId);

	List<ReservationPrice> getPriceList(int reservationInfoId);

	CommentResponse getComment(int reservationInfoId);

	int modifyReservation(int reservationInfoId);

	int addComment(MultipartFile file, Comment comment);
	
	void uploadFile(MultipartFile file, int commentId, int reservationInfoId);

	String getRandReservationDate();
}
