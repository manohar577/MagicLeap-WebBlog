package com.magicleap.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magicleap.ws.model.User;


@Service("UserService")
public interface UserService {
	
	User findById(String id, String password);
	
	User findById(String id);
	
	boolean saveUser(User user);
	
	boolean updateUser(User currentUser, User user);
	
	List<User> findAllUsers(); 
	
}
