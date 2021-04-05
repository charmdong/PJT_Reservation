package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Product;
import com.nts.reservation.repository.ProductDao;
import com.nts.reservation.service.ProductService;

/**
 * 상품 service implement
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	/**
	 * @param categoryId
	 * @param start
	 * @return productList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Product> getProducts(int categoryId, int start) {
		if(categoryId == ProductService.TOTAL) {
			return productDao.selectAllProducts(start, ProductService.LIMIT);
		} else {
			return productDao.selectProductsByCategory(categoryId, start, ProductService.LIMIT);
		}
	}

	/**
	 * @param categoryId
	 * @return displayCount
	 */
	@Override
	@Transactional(readOnly = true)
	public int getDisplayCount(int categoryId) {
		if(categoryId == ProductService.TOTAL) {
			return productDao.selectTotalDisplayCount();
		} else {
			return productDao.selectDisplayCountsByCategory(categoryId);
		}
	}

	/**
	 * @param product
	 * @return product
	 */
	/*
	 * @Override
	 * 
	 * @Transactional public Product addProduct(Product product) { int id =
	 * productDao.insert(product); product.setId(id);
	 * 
	 * return product; }
	 */

	/**
	 * @param product
	 * @return updateCount
	 */
	/*
	 * @Override
	 * 
	 * @Transactional public int updateProduct(Product product) { return
	 * productDao.update(product); }
	 */ 

	/**
	 * @param id
	 * @return deleteCount
	 */
	/*
	 * @Override
	 * 
	 * @Transactional public int deleteProduct(int id) { return
	 * productDao.deleteById(id); }
	 */
}
