package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;
import com.nts.reservation.reponse.DisplayInfoResponse;
import com.nts.reservation.reponse.ProductResponse;
import com.nts.reservation.service.DisplayService;
import com.nts.reservation.service.ProductService;

/**
 * 상품 API
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 19.
 */
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayService displayService;

	/**
	 * @param start
	 * @param categoryId
	 * @return productResponse
	 */
	@GetMapping
	public ProductResponse getProductList(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId) {
		ProductResponse productResponse = new ProductResponse();
		List<Product> productList = productService.getProducts(categoryId, start);
		int totalCount = productService.getDisplayCount(categoryId);

		productResponse.setItems(productList);
		productResponse.setTotalCount(totalCount);

		return productResponse;
	}

	/**
	 * @param displayInfoId
	 * @param limit
	 * @return displayInfoResponse
	 */
	@GetMapping(path = "/{displayInfoId}")
	public DisplayInfoResponse getDisplay(@PathVariable("displayInfoId") int displayInfoId,
			@RequestParam(name = "limit", required = false, defaultValue = "0") int limit) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		double averageScore = displayService.getAverageScore(displayInfoId);
		List<Comment> commentList = displayService.getComments(displayInfoId, limit);
		DisplayInfo displayInfo = displayService.getDisplayInfo(displayInfoId);
		DisplayImage displayInfoImage = displayService.getDisplayImage(displayInfoId);
		List<ProductImage> productImageList = displayService.getProductImages(displayInfoId);
		List<ProductPrice> productPriceList = displayService.getProductPrices(displayInfoId);
		int commentCount = displayService.getCommentCount(displayInfoId);

		displayInfoResponse.setAverageScore(averageScore);
		displayInfoResponse.setComments(commentList);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);
		displayInfoResponse.setProductImages(productImageList);
		displayInfoResponse.setProductPrices(productPriceList);
		displayInfoResponse.setCommentCount(commentCount);

		return displayInfoResponse;
	}
}
