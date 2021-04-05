package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.reponse.CommentResponse;
import com.nts.reservation.reponse.ReservationInfoResponse;
import com.nts.reservation.reponse.ReservationResponse;
import com.nts.reservation.service.ReservationService;

/**
 * 예약 정보 API
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 6.
 */
@RestController
@RequestMapping(path = "/api/reservations", method = RequestMethod.PUT)
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	/**
	 * @param reservationEmail
	 * @return reservationInfoResponse
	 */
	@GetMapping
	public ReservationInfoResponse getReservationList(
			@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		List<ReservationInfo> reservationInfoList = reservationService.getReservationInfos(reservationEmail);

		reservationInfoResponse.setReservations(reservationInfoList);
		reservationInfoResponse.setSize(reservationInfoList.size());

		return reservationInfoResponse;
	}

	/**
	 * @param reservationParam
	 * @return reservationResponse
	 */
	@PostMapping
	public ReservationResponse addReservation(@RequestBody ReservationParam reservationParam) {
		int reservationInfoId = reservationService.insertReservation(reservationParam);

		ReservationInfo reservationInfo = reservationService.getReservationInfo(reservationInfoId);
		List<ReservationPrice> reservationPriceList = reservationService.getPriceList(reservationInfoId);

		return new ReservationResponse(reservationInfo, reservationPriceList);
	}

	/**
	 * @param reservationId
	 * @return reservationResponse
	 */
	@PutMapping(path = "/{reservationId}")
	public ReservationResponse cancelReservation(@PathVariable("reservationId") int reservationInfoId) {
		reservationService.modifyReservation(reservationInfoId);

		ReservationInfo reservationInfo = reservationService.getReservationInfo(reservationInfoId);
		List<ReservationPrice> reservationPriceList = reservationService.getPriceList(reservationInfoId);

		return new ReservationResponse(reservationInfo, reservationPriceList);
	}

	/**
	 * @param file
	 * @param reservationInfoId
	 * @param comment
	 * @param productId
	 * @param score
	 * @return commentResponse
	 */
	@PostMapping(path = "/{reservationInfoId}/comments")
	public CommentResponse addComment(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestBody Comment comment) {
		int commentId = reservationService.addComment(file, comment);
		return reservationService.getComment(commentId);
	}
}
