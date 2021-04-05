package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category;
import com.nts.reservation.reponse.CategoryResponse;
import com.nts.reservation.service.CategoryService;

/**
 * 카테고리 API
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 19.
 */
@RestController
@RequestMapping(path = "/api")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * @return categoryResponse
	 */
	@GetMapping(path = "/categories")
	public CategoryResponse getCategoryList() {
		CategoryResponse categoryResponse = new CategoryResponse();
		List<Category> categoryList = categoryService.getCategories();
		
		categoryResponse.setItems(categoryList);
		return categoryResponse;
	}
}
