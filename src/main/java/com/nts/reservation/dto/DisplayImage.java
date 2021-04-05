package com.nts.reservation.dto;

/**
 * 공연 이미지 dto
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 31.
 */
public class DisplayImage extends FileInfo {
	private int displayInfoId;
	private int displayInfoImageId;
	private int fileId;

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}

	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "displayImageDto [displayInfoId=" + displayInfoId + ", displayInfoImageId=" + displayInfoImageId
				+ ", fileId=" + fileId + "]";
	}

}
