package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.Promotion;

/**
 * 프로모션 model
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 25.
 */
public class PromotionResponse {
	private List<Promotion> items;

	public List<Promotion> getItems() {
		return items;
	}

	public void setItems(List<Promotion> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PromotionResponse [items=" + items + "]";
	}
}
