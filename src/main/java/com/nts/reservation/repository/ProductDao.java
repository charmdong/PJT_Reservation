package com.nts.reservation.repository;

import static com.nts.reservation.sql.DisplaySqls.SELECT_PRODUCT_IMAGES;
import static com.nts.reservation.sql.DisplaySqls.SELECT_PRODUCT_PRICE;
import static com.nts.reservation.sql.ProductDaoSqls.AND;
import static com.nts.reservation.sql.ProductDaoSqls.COMPARE_CATEGORYID;
import static com.nts.reservation.sql.ProductDaoSqls.LIMIT;
import static com.nts.reservation.sql.ProductDaoSqls.SELECT_DISPLAY_COUNT;
import static com.nts.reservation.sql.ProductDaoSqls.SELECT_PRODUCT_INFO;
import static com.nts.reservation.sql.ProductDaoSqls.WHERE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * 상품 dao
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 17.
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> imageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> priceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	/**
	 * @param dataSource
	 */
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @param displayInfoId
	 * @return productImageList
	 */
	public List<ProductImage> selectProductImages(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGES, params, imageRowMapper);
	}

	/**
	 * @param displayInfoId
	 * @return productPriceList
	 */
	public List<ProductPrice> selectProductPrices(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICE, params, priceRowMapper);
	}

	/**
	 * @param start
	 * @param limit
	 * @return productList
	 */
	public List<Product> selectAllProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();

		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(SELECT_PRODUCT_INFO + LIMIT, params, productRowMapper);
	}

	/**
	 * @param categoryId
	 * @param start
	 * @param limit
	 * @return productListByCategory
	 */
	public List<Product> selectProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();

		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);

		return jdbc.query(SELECT_PRODUCT_INFO + WHERE + COMPARE_CATEGORYID + LIMIT, params, productRowMapper);
	}

	/**
	 * @return totalDisplayCount
	 */
	public Integer selectTotalDisplayCount() {
		return jdbc.queryForObject(SELECT_DISPLAY_COUNT, Collections.emptyMap(), Integer.class);
	}

	/**
	 * @param categoryId
	 * @return displayCountByCategory
	 */
	public Integer selectDisplayCountsByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_DISPLAY_COUNT + AND + COMPARE_CATEGORYID, params, Integer.class);
	}
}
