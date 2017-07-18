package com.magicleap.ws.controller;
 
import java.util.List;
 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 



import com.magicleap.ws.model.User;
import com.magicleap.ws.service.UserService;
 
@RestController
public class UserController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work

     
    //-------------------get all Users--------------------------------------------------------
    
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
       
    	return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }
 
   //-------------------Validate User--------------------------------------------------------
     
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@RequestBody User user) {
        System.out.println("Fetching User with id " + user.getUserId());
        User userRet = userService.findById(user.getUserId(), user.getPassword());
        if (userRet == null) {
            System.out.println("User with id " + user.getUserId() + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getName());
 
        User userRet = userService.findById(user.getUserId(), user.getPassword());
        System.out.println("after find");
        if (userRet != null ) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
        
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        if(userService.updateUser(currentUser, user))
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        else return new ResponseEntity<User>(currentUser, HttpStatus.NOT_IMPLEMENTED);
    }
 
}