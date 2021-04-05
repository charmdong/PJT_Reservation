package com.nts.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 상품 dto
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
@JsonIgnoreProperties({"categoryId", "event", "createDate", "modifyDate"})
public class Product {
	private int id;
	private int categoryId;
	private String description;
	private String content;
	private String event;
	private String createDate;
	private String modifyDate;
	
	/**
	 * 쿼리를 통해 전달되는 추가적 변수
	 */
	private int displayInfoId;
	private String placeName;
	private String productImageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", categoryId=" + categoryId + ", description=" + description + ", content="
				+ content + ", event=" + event + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
