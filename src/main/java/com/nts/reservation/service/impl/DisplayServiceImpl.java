package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.DisplayImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;
import com.nts.reservation.repository.CommentDao;
import com.nts.reservation.repository.DisplayInfoDao;
import com.nts.reservation.repository.ProductDao;
import com.nts.reservation.service.DisplayService;

/**
 * 공연정보 service implement
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
@Service
public class DisplayServiceImpl implements DisplayService {
	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CommentDao commentDao;

	/**
	 * @param displayInfoId
	 * @return averageScore
	 */
	@Override
	@Transactional(readOnly = true)
	public double getAverageScore(int displayInfoId) {
		return displayInfoDao.selectAverageScore(displayInfoId);
	}

	/**
	 * @param displayInfoId
	 * @return displayInfo
	 */
	@Override
	@Transactional(readOnly = true)
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	/**
	 * @param displayInfoId
	 * @param limit
	 * @return commentList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getComments(int displayInfoId, int limit) {
		List<Comment> commentList;
		
		if(limit == 0) {
			commentList = commentDao.selectAllComments(displayInfoId);
		} else {
			commentList = commentDao.selectComments(displayInfoId, limit);
		}
		
		for (Comment comment : commentList) {
			List<CommentImage> commentImageList = commentDao.selectCommentImages(comment.getCommentId());
			comment.setCommentImages(commentImageList);
			
			String reservationEmail = comment.getReservationEmail();
			reservationEmail = reservationEmail.substring(0, 4) + "****";
			comment.setReservationEmail(reservationEmail);
		}

		return commentList;
	}

	/**
	 * @param displayInfoId
	 * @return displayInfoImage
	 */
	@Override
	@Transactional(readOnly = true)
	public DisplayImage getDisplayImage(int displayInfoId) {
		return displayInfoDao.selectDisplayImage(displayInfoId);
	}

	/**
	 * @param displayInfoId
	 * @return productImageList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProductImage> getProductImages(int displayInfoId) {
		return productDao.selectProductImages(displayInfoId);
	}

	/**
	 * @param displayInfoId
	 * @return productPriceList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		List<ProductPrice> productPriceList = productDao.selectProductPrices(displayInfoId);
		
		for(ProductPrice productPrice : productPriceList) {
			String priceType = productPrice.getPriceTypeName();
			productPrice.setPriceTypeName(DisplayService.SEAT.valueOf(priceType).getSeatType());
		}
		
		return productPriceList;
	}

	/**
	 * @param displayInfoId
	 * @return commentCount
	 */
	@Override
	@Transactional(readOnly = true)
	public int getCommentCount(int displayInfoId) {
		return commentDao.selectCommentCount(displayInfoId);
	}
}
