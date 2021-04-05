package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

/**
 * 상품 service 
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 17.
 */
public interface ProductService {
	int LIMIT = 4;
	int TOTAL = 0;
	
	List<Product> getProducts(int categoryId, int start);
	int getDisplayCount(int categoryId);
	// Product addProduct(Product product);
	// int updateProduct(Product product);
	// int deleteProduct(int id);
}