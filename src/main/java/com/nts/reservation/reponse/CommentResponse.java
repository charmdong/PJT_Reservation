package com.nts.reservation.reponse;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;

/**
 * 
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 5.
 */
public class CommentResponse {
	private String comment;
	private int commentId;
	private CommentImage commentImage;
	private String create_date;
	private String modify_date;
	private int productId;
	private int reservationInfoId;
	private int score;

	public CommentResponse() {

	}

	public CommentResponse(Comment comment, CommentImage commentImage) {
		this.comment = comment.getComment();
		this.commentId = comment.getCommentId();
		this.commentImage = commentImage;
		this.create_date = comment.getCreateDate();
		this.modify_date = comment.getModifyDate();
		this.productId = comment.getProductId();
		this.reservationInfoId = comment.getReservationInfoId();
		this.score = (int) comment.getScore();
	}

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

	public CommentImage getCommentImage() {
		return commentImage;
	}

	public void setCommentImage(CommentImage commentImage) {
		this.commentImage = commentImage;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
