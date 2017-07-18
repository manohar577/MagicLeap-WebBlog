package com.magicleap.ws.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magicleap.ws.dao.UserServiceDao;
import com.magicleap.ws.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{
	
	
@Autowired
UserServiceDao userServiceDao;

	public List<User> findAllUsers() {
		return userServiceDao.getAllUsers();
	}
	
	public User findById(String id, String password) {
		User user = userServiceDao.getUserById(id, password);
			if(user.getUserId() != null && user.getUserId().equals(id) && user.getPassword().equals(password)) {
				return user;
			}
			else return null;
	}
	
	public User findById(String id) {
		User user = userServiceDao.getUserById(id);
			if(user.getUserId() != null && user.getUserId().equals(id)) {
				return user;
			}
			else return null;
	}
	
	public boolean saveUser(User user) {

		if( userServiceDao.addUser(user)) 
			return true;
		
		else return false;
	}

	public boolean updateUser(User currentUser, User user) {
		boolean ret = false;
		
        if(user.getName() != null)
		currentUser.setName(user.getName());
        if(user.getAddress() != null)
        currentUser.setAddress(user.getAddress());
        if(user.getPhone() != null)
        currentUser.setPhone(user.getPhone());
        if(user.getPassword() != null)
        currentUser.setPassword(user.getPassword());
        if(user.getEmail() != null)
        currentUser.setEmail(user.getEmail());
        
        try {
        	userServiceDao.updateUser(currentUser);
        	ret = true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

}
