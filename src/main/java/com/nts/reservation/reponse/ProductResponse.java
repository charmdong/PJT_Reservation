package com.nts.reservation.reponse;

import java.util.List;

import com.nts.reservation.dto.Product;

/**
 * 상품 model
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 25.
 */
public class ProductResponse {
	private int totalCount;
	private List<Product> items;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ProductResponse [totalCount=" + totalCount + ", items=" + items + "]";
	}
}
