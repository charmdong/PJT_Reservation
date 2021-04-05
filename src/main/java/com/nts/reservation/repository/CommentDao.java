package com.nts.reservation.repository;

import static com.nts.reservation.sql.DisplaySqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.FileInfo;

/**
 * 한줄평 Dao
 * 
 * @author : donggun.chung
 * @version : 1.0
 * @since : 2019. 7. 29.
 */
@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert commentInsertion;
	private SimpleJdbcInsert commentImageInsertion;
	private SimpleJdbcInsert fileInsertion;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	/**
	 * @param dataSource
	 */
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.commentInsertion = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		this.commentImageInsertion = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
		this.fileInsertion = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
	}

	/**
	 * @param displayInfoId
	 * @return totalCommentList
	 */
	public List<Comment> selectAllComments(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENTS, params, commentRowMapper);
	}

	/**
	 * @param displayInfoId
	 * @param limit
	 * @return commentList
	 */
	public List<Comment> selectComments(int displayInfoId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("limit", limit);
		return jdbc.query(SELECT_COMMENTS + LIMIT, params, commentRowMapper);
	}
	
	/**
	 * @param reservationInfoId
	 * @return comment
	 */
	public Comment selectComment(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		return jdbc.queryForObject(SELECT_COMMENT, params, commentRowMapper);
	}
	
	/**
	 * @param comment
	 * @return commentId
	 */
	public int insertComment(Comment comment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		return commentInsertion.executeAndReturnKey(params).intValue();
	}
	
	/**
	 * @param commentImage
	 * @return commentImageId
	 */
	public int insertCommentImage(CommentImage commentImage) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(commentImage);
		return commentImageInsertion.executeAndReturnKey(params).intValue();
	}
	
	/**
	 * @param file
	 * @return fileInfoId
	 */
	public int insertFile(FileInfo file) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);
		return fileInsertion.executeAndReturnKey(params).intValue();
	}
	
	/**
	 * @param reservationUserCommentId
	 * @return commentImage
	 */
	public CommentImage selectCommentImage(int reservationUserCommentId) {
		CommentImage commentImage;
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationUserCommentId", reservationUserCommentId);
		
		try {
			commentImage = jdbc.queryForObject(SELECT_COMMENT_IMAGE, params, commentImageRowMapper);
		} catch(EmptyResultDataAccessException exception) {
			return null;
		}
		
		return commentImage;	
	}
	
	/**
	 * @param imageId
	 * @return commentImage
	 */
	public CommentImage selectCommentImageById(int imageId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("imageId", imageId);
		return jdbc.queryForObject(SELECT_COMMENT_IMAGE_BY_ID, params, commentImageRowMapper);
	}
	
	/**
	 * @param displayInfoId
	 * @return commentCount
	 */
	public int selectCommentCount(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_COMMENT_COUNT, params, Integer.class);
	}
	
	/**
	 * @param displayInfoId
	 * @return commentImageList
	 */
	public List<CommentImage> selectCommentImages(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		return jdbc.query(SELECT_COMMENT_IMAGES, params, commentImageRowMapper);
	}
}
