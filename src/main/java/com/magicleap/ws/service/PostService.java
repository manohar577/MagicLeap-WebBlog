package com.magicleap.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magicleap.ws.model.Post;


@Service("PostService")
public interface PostService {
	
	
	boolean savePost(Post post);
	
	boolean updatePost(Post currentPost, Post post);
	
	boolean deletePost(String postId);
	
	List<Post> getAllPosts(); 
	
	List<Post> findPostByUserId(String userId);
	
	Post findPostById(String postId);
	
}
