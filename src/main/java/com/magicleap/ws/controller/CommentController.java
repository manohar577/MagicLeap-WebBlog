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

import com.magicleap.ws.model.Comment;
import com.magicleap.ws.service.CommentService;
 
@RestController
public class CommentController {
 
    @Autowired
    CommentService commentService; 

     
    
    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getComments() {
       
    	return new ResponseEntity<List<Comment>>(commentService.getAllComments(), HttpStatus.OK);
    }
 
     
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("id") String postId) {
        System.out.println("Fetching All comments for post with id " + postId);
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        if (comments!= null && comments.isEmpty()) {
            System.out.println("No comment exist with id " + postId);
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
 
    
   @RequestMapping(value = "/comment/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable("id") String userId) {
       System.out.println("Fetching All comments for user with id " + userId);
       List<Comment> comments = commentService.findCommentsByUserId(userId);
       if (comments!= null && comments.isEmpty()) {
           System.out.println("No comment exist with id " + userId);
           return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
   }

     
     
    @RequestMapping(value = "/comment/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createComment(@RequestBody Comment comment) {
        System.out.println("Creating Post " + comment.getDetails());
 

        if(commentService.saveComment(comment))
        return new ResponseEntity<Void>( HttpStatus.CREATED);
        else return new ResponseEntity<Void>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/comment/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
        System.out.println("deleting comment " + id);
 
        Comment Ret = commentService.findCommentById(id);
        if (Ret == null ) {
            System.out.println("A Comment with id " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        if(commentService.deleteComment(id))
        return new ResponseEntity<Void>( HttpStatus.OK);
        else return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
    }
    
     
     
    @RequestMapping(value = "/comment/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updatePost(@PathVariable("id") String id, @RequestBody Comment comment) {
        System.out.println("Updating comment " + id);
         
        Comment currentComment = commentService.findCommentById(id);
        
        if (currentComment==null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
 
        if(commentService.updateComment(currentComment, comment))
        return new ResponseEntity<Comment>(currentComment, HttpStatus.OK);
        else return new ResponseEntity<Comment>(currentComment, HttpStatus.NOT_IMPLEMENTED);
    }
 
}