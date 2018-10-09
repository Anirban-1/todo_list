package com.todolist.app.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.app.model.*;

@Service
public class UserService {

	@Autowired
	private UserDTO repository;

	public User findByUserName(String userName) {

		Iterator<User> iter = repository.findAll().iterator();
		while (iter.hasNext()) {
			User u = iter.next();
			if (u.getUserName().equals(userName))
				return u;
		}
		return null;
	}

	public void saveUser(User user) {
		if (user != null && repository.findByUserName(user.getUserName()) == null) {
			repository.save(user);
		}
	}

	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}
}
