package com.nts.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.reponse.CommentResponse;
import com.nts.reservation.repository.CommentDao;
import com.nts.reservation.repository.DisplayInfoDao;
import com.nts.reservation.repository.ReservationDao;
import com.nts.reservation.service.ReservationService;

/**
 * 예약 정보 service implement
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 6.
 */
@Service
@PropertySource("classpath:application.properties")
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Autowired
	private CommentDao commentDao;

	@Value("${dir.path}")
	private String directoryPath;
	
	/**
	 * @param reservationEmail
	 * @return reservationInfoList
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ReservationInfo> getReservationInfos(String reservationEmail) {
		List<ReservationInfo> reservationInfoList = reservationDao.selectReservationInfoList(reservationEmail);

		for (ReservationInfo reservationInfo : reservationInfoList) {
			DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(reservationInfo.getDisplayInfoId());
			reservationInfo.setDisplayInfo(displayInfo);
		}

		return reservationInfoList;
	}

	/**
	 * @param reservationParam
	 * @return reservationInfoId
	 */
	@Override
	@Transactional
	public int insertReservation(ReservationParam reservationParam) {
		ReservationInfo reservationInfo = new ReservationInfo(reservationParam);
		int reservationInfoId = reservationDao.insertReservationInfo(reservationInfo);

		List<ReservationPrice> reservationPriceList = reservationParam.getPrices();

		for (ReservationPrice reservationPrice : reservationPriceList) {
			reservationPrice.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(reservationPrice);
		}

		return reservationInfoId;
	}

	/**
	 * @param reservationInfoId
	 * @return reservationInfo
	 */
	@Override
	@Transactional
	public ReservationInfo getReservationInfo(int reservationInfoId) {
		return reservationDao.selectReservationInfo(reservationInfoId);
	}

	/**
	 * @param reservationInfoId
	 * @return reservationPriceList
	 */
	@Override
	@Transactional
	public List<ReservationPrice> getPriceList(int reservationInfoId) {
		return reservationDao.selectReservationPriceList(reservationInfoId);
	}

	/**
	 * @param reservationInfoId
	 * @return updateCount
	 */
	@Override
	@Transactional
	public int modifyReservation(int reservationInfoId) {
		return reservationDao.updateReservation(reservationInfoId);
	}

	/**
	 * @param reservationInfoId
	 * @return commentResponse
	 */
	@Override
	@Transactional
	public CommentResponse getComment(int commentId) {
		Comment comment = commentDao.selectComment(commentId);
		CommentImage commentImage = commentDao.selectCommentImage(commentId);

		return new CommentResponse(comment, commentImage);
	}

	/**
	 * @param comment
	 * @param productId
	 * @param reservationInfoId
	 * @param score
	 * @return commentId
	 */
	@Override
	public int addComment(MultipartFile file, Comment comment) { 
		Comment commentDto = new Comment();
		String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		int reservationInfoId = comment.getReservationInfoId();
		
		commentDto.setComment(comment.getComment());
		commentDto.setProductId(comment.getProductId());
		commentDto.setReservationInfoId(reservationInfoId);
		commentDto.setScore(comment.getScore());
		commentDto.setCreateDate(currentDateTime);
		commentDto.setModifyDate(currentDateTime);

		int commentId = commentDao.insertComment(commentDto);

		if (file != null) {
			uploadFile(file, commentId, reservationInfoId);
		}

		return commentId;
	}

	@Override
	public void uploadFile(MultipartFile file, int commentId, int reservationInfoId) {
		FileInfo fileInfo = new FileInfo(file);
		int fileInfoId = commentDao.insertFile(fileInfo);

		CommentImage commentImage = new CommentImage();
		commentImage.setReservationInfoId(reservationInfoId);
		commentImage.setFileId(fileInfoId);
		commentImage.setReservationUserCommentId(commentId);

		commentDao.insertCommentImage(commentImage);
		try (FileOutputStream fos = new FileOutputStream(directoryPath + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	/**
	 * @return randReservationDate
	 */
	@Override
	public String getRandReservationDate() {
		int randNum = (int) (Math.random() * 5) + 1;
		LocalDate randDate = LocalDate.now().plusDays(randNum);

		return randDate.getYear() + ". " + randDate.getMonthValue() + ". " + randDate.getDayOfMonth();
	}
}
