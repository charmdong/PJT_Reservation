package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Promotion;
import com.nts.reservation.repository.PromotionDao;
import com.nts.reservation.service.PromotionService;

/**
 * 프로모션 service implement
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionDao promotionDao;

	/**
	 * @return promotionList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Promotion> getPromotions() {
		return promotionDao.selectAll();
	}

	/**
	 * @param promotion
	 * @return promotion
	 */
	@Override
	@Transactional
	public Promotion addPromotion(Promotion promotion) {
		int id = promotionDao.insert(promotion);
		promotion.setId(id);

		return promotion;
	}

	/**
	 * @param id
	 * @param productId
	 * @return updateCount
	 */
	@Override
	@Transactional
	public int updatePromotion(int id, int productId) {
		return promotionDao.updateProduct(id, productId);
	}

	/**
	 * @param id
	 * @return deleteCount
	 */
	@Override
	@Transactional
	public int deletePromotion(int id) {
		return promotionDao.deleteById(id);
	}

}
