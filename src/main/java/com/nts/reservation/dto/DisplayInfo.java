package com.nts.reservation.dto;

/**
 * 공연정보 dto
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
public class DisplayInfo {
	private int displayInfoId;
	private int productId;
	private String email;
	private String homePage;
	private String createDate;
	private String modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String telephone;

	/**
	 * 쿼리를 통해 전달되는 추가적 변수
	 */
	private int categoryId;
	private String categoryName;
	private String productContent;
	private String productDescription;
	private String productEvent;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
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

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	@Override
	public String toString() {
		return "DisplayInfoDto [displayInfoId=" + displayInfoId + ", productId=" + productId + ", email=" + email
				+ ", homePage=" + homePage + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", openingHours=" + openingHours + ", placeLot=" + placeLot + ", placeName=" + placeName
				+ ", placeStreet=" + placeStreet + ", telephone=" + telephone + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", productContent=" + productContent + ", productDescription="
				+ productDescription + ", productEvent=" + productEvent + "]";
	}

}
