package com.nts.reservation.dto;

/**
 * 상품 이미지 dto
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 31.
 */
public class ProductImage extends FileInfo {
	private int productId;
	private int productImageId;
	private String type;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "productImageDto [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
				+ "]";
	}

}
