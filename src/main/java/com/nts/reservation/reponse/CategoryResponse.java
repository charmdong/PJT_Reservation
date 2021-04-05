package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.Category;

/**
 * 카테고리 model
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 25.
 */
public class CategoryResponse {
	private List<Category> items;

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CategoryResponse [items=" + items + "]";
	}
}
