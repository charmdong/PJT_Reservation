package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Category;
import com.nts.reservation.repository.CategoryDao;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리 service implement
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 18.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * @return categoryResponse
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Category> getCategories(){
		return categoryDao.selectAll();
	}

	/**
	 * @param category
	 * @return category
	 */
	@Override
	@Transactional
	public Category addCategory(Category category) {
		int id = categoryDao.insert(category);
		category.setId(id);

		return category;
	}

	/**
	 * @param id
	 * @param name
	 * @return updateCount
	 */
	@Override
	@Transactional
	public int updateCategory(int id, String name) {
		return categoryDao.updateName(id, name);
	}

	/**
	 * @param id
	 * @return deleteCount
	 */
	@Override
	@Transactional
	public int deleteCategory(int id) {
		return categoryDao.deleteById(id);
	}

}
