package com.magicleap.ws.controller;
 
import java.util.List;
 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.magicleap.ws.model.Post;
import com.magicleap.ws.service.PostService;
 
@RestController
public class PostController {
 
    @Autowired
    PostService postService; 

     
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPosts() {
       
    	return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
    }
 
     
    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPost(@PathVariable("id") String userId) {
        System.out.println("Fetching All posts for User with id " + userId);
        List<Post> posts = postService.findPostByUserId(userId);
        if (posts!=null &&  posts.isEmpty()) {
            System.out.println("No posts exist with id " + userId);
            return new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
 
     
     
    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        System.out.println("Creating Post " + post.getPostdetails());
 

        if(postService.savePost(post))
        return new ResponseEntity<Void>( HttpStatus.CREATED);
        else return new ResponseEntity<Void>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/post/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
        System.out.println("deleting Post " + id);
 
        Post postRet = postService.findPostById(id);
        if (postRet == null ) {
            System.out.println("A Post with id " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        postService.deletePost(id);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
    
     
     
    @RequestMapping(value = "/post/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable("id") String id, @RequestBody Post post) {
        System.out.println("Updating post " + id);
         
        Post currentPost = postService.findPostById(id);
        
        if (currentPost==null) {
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
 
        if(postService.updatePost(currentPost, post))
        return new ResponseEntity<Post>(currentPost, HttpStatus.OK);
        else return new ResponseEntity<Post>(currentPost, HttpStatus.NOT_IMPLEMENTED);
    }
 
}