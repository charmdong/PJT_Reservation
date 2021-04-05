package com.nts.reservation.dto;

import java.util.List;

/**
 * reservation_user_comment dto
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 29.
 */
public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private String createDate;
	private String modifyDate;
	private int productId;
	private int reservationInfoId;
	private double score;

	/**
	 * 쿼리를 통해 전달되는 추가적 변수
	 */
	private String reservationDate;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", commentId=" + commentId + ", commentImages=" + commentImages
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
				+ ", reservationInfoId=" + reservationInfoId + ", score=" + score + ", reservationDate="
				+ reservationDate + ", reservationEmail=" + reservationEmail + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + "]";
	}

}
