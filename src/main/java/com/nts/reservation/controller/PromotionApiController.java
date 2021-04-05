package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Promotion;
import com.nts.reservation.reponse.PromotionResponse;
import com.nts.reservation.service.PromotionService;

/**
 * 프로모션 API
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 19.
 */
@RestController
@RequestMapping(path = "/api")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	/**
	 * @return promotionResponse
	 */
	@GetMapping(path = "/promotions")
	public PromotionResponse getPromotionList() {
		PromotionResponse promotionResponse = new PromotionResponse();
		List<Promotion> promotionList = promotionService.getPromotions();
		
		promotionResponse.setItems(promotionList);
		return promotionResponse;
	}
}
