package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * 공연정보 service
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
public interface DisplayService {
	enum SEAT {
		A("성인"), Y("청소년"), B("유아"), V("V석"), R("R석"), S("S석"), D("장애인"), E("얼리버드");
			
		private String seatType;

		SEAT(String seatType) {
			this.seatType = seatType;
		}
		
		public String getSeatType() {
			return seatType;
		}
	}
	
	double getAverageScore(int displayInfoId);
	DisplayInfo getDisplayInfo(int displayInfoId);
	List<Comment> getComments(int displayInfoId, int limit);
	DisplayImage getDisplayImage(int displayInfoId);
	List<ProductImage> getProductImages(int displayInfoId);
	List<ProductPrice> getProductPrices(int displayInfoId);
	int getCommentCount(int displayInfoId);
}
