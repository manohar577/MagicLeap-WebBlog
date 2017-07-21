package com.magicleap.ws.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magicleap.ws.dao.PostServiceDao;
import com.magicleap.ws.model.Post;

@Service("PostService")
@Transactional
public class PostServiceImpl implements PostService{
	
	
@Autowired
PostServiceDao postServiceDao;

	public List<Post> getAllPosts() {
		return postServiceDao.getAllPosts();
	}
	
	public List<Post> findPostByUserId(String userId) {
		List<Post> posts = postServiceDao.getAllPostByUserId(userId);
			if(!(posts.isEmpty())) {
				return posts;
			}
			else return null;
	}
	
	public Post findPostById(String postId) {
		Post post = postServiceDao.getPostById(postId);
			if(post.getUserid() != null && post.getPostid().equals(postId)) {
				return post;
			}
			else return null;
	}
	
	public boolean savePost(Post post) {

		if( postServiceDao.addPost(post)) 
			return true;
		
		else return false;
	}

	public boolean updatePost(Post currentPost, Post post) {
		boolean ret = false;
		
        if(post.getUserid() != null)
        	currentPost.setUserid(post.getUserid());
        if(post.getPostdetails() != null)
        	currentPost.setPostdetails(post.getPostdetails());
        if(post.getPostdate() != null)
        	currentPost.setPostdate(post.getPostdate());
        if(post.getPosttitle() != null)
        	currentPost.setPosttitle(post.getPosttitle());
        
        try {
        	postServiceDao.updatePost(currentPost);
        	ret = true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

	public boolean deletePost(String postId) {
		if(postServiceDao.deletePost(postId))
			return true;
		else return false;
	}

}
