package com.magicleap.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magicleap.ws.model.Comment;


@Service("CommentService")
public interface CommentService {
	
	
	boolean saveComment(Comment comment);
	
	boolean updateComment(Comment currentComment, Comment comment);
	
	boolean deleteComment(String commentId);
	
	List<Comment> getAllComments(); 
	
	List<Comment> findCommentsByPostId(String postId);
	
	List<Comment> findCommentsByUserId(String userId);
	
	Comment findCommentById(String commentId);
	
}
