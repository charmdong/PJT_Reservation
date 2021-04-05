package com.nts.reservation.dto;

/**
 * 한줄평 이미지 dto
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 31.
 */
public class CommentImage extends FileInfo {
	private int imageId;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "CommentImage [imageId=" + imageId + ", reservationInfoId=" + reservationInfoId
				+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + "]";
	}
}
