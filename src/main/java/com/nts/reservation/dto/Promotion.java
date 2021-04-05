package com.nts.reservation.dto;

/**
 * 프로모션 dto
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
public class Promotion {
	private int id;
	private int productId;
	
	/**
	 * 쿼리를 통해 전달되는 추가적 변수
	 */
	private String productImageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "PromotionDto [id=" + id + ", productId=" + productId + ", productImageUrl=" + productImageUrl + "]";
	}
}
