package com.todolist.app.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.todolist.app.model.*;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User findByUserName(User user) {
		
		Iterator<User> iter = repository.findAll().iterator();
		while (iter.hasNext()) {
			User u = iter.next();
			if (u.getUserName().equals(user.getUserName()))
				return u;
		}
		return null;
	}

	public void saveUser(User user) {
		if (user != null && findByUserName(user) == null) {
			repository.save(user);
		}
		
	}
	
	public Iterable<User> findAllUsers(){
		return repository.findAll();
	}
}
