package com.magicleap.ws.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magicleap.ws.dao.CommentServiceDao;
import com.magicleap.ws.model.Comment;

@Service("CommentService")
@Transactional
public class CommentServiceImpl implements CommentService{
	
	
@Autowired
CommentServiceDao commentServiceDao;

	public List<Comment> getAllComments() {
		return commentServiceDao.getAllComments();
	}
	
	public List<Comment> findCommentsByUserId(String userId) {
		List<Comment> comments = commentServiceDao.getAllCommentsByUserId(userId);
			if(comments != null && !(comments.isEmpty())) {
				return comments;
			}
			else return null;
	}
	
	public List<Comment> findCommentsByPostId(String postId) {
		List<Comment> comments = commentServiceDao.getCommentsByPostId(postId);
		if(comments != null && !(comments.isEmpty())) {
			return comments;
		}
		else return null;
	}
	
	public boolean saveComment(Comment comment) {

		if( commentServiceDao.addComment(comment)) 
			return true;
		
		else return false;
	}

	public boolean updateComment(Comment currentComment, Comment comment) {
		boolean ret = false;
		
        if(comment.getUserid() != null)
        	currentComment.setUserid(comment.getUserid());
        if(comment.getPostid() != null)
        	currentComment.setPostid(comment.getPostid());
        if(comment.getDetails() != null)
        	currentComment.setDetails(comment.getDetails());
        if(comment.getDatetime() != null)
        	currentComment.setDatetime(comment.getDatetime());
        
        try {
        	commentServiceDao.updateComment(currentComment);
        	ret = true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

	public boolean deleteComment(String commentId) {
		if(commentServiceDao.deleteComment(commentId))
			return true;
		else return false;
	}

	public Comment findCommentById(String commentId) {
		
		return commentServiceDao.getCommentById(commentId);
	}

}
