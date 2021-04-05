package com.nts.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 정보 dto
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
public class FileInfo {
	private int fileInfoId;
	private String fileName;
	private String saveFileName;
	private String modifyDate;
	private String contentType;
	private String createDate;
	private boolean deleteFlag;

	public FileInfo() {
		
	}
	
	public FileInfo(MultipartFile file) {
		this.contentType = file.getContentType();
		this.createDate = this.modifyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.fileName = file.getOriginalFilename();
		this.saveFileName = this.fileName;
		this.deleteFlag = false;
	}
	
	public int getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(int fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "FileInfoDto [fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", saveFileName=" + saveFileName
				+ ", modifyDate=" + modifyDate + ", contentType=" + contentType + ", createDate=" + createDate
				+ ", deleteFlag=" + deleteFlag + "]";
	}

}
