package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Promotion;

/**
 * 프로모션 service 인터페이스
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
public interface PromotionService {
	List<Promotion> getPromotions();
	Promotion addPromotion(Promotion promotion);
	int updatePromotion(int id, int productId);
	int deletePromotion(int id);
}
