package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Category;

/**
 * 카테고리 service 인터페이스
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
public interface CategoryService {
	List<Category> getCategories();
	Category addCategory(Category category);
	int updateCategory(int id, String name);
	int deleteCategory(int id);
}
