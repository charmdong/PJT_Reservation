package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * 공연정보 model
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
public class DisplayInfoResponse {
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;
	private int commentCount;

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public DisplayImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
