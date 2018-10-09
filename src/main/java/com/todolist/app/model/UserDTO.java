package com.todolist.app.model;

import org.springframework.data.repository.CrudRepository;

public interface UserDTO extends CrudRepository<User, Integer> {
	User findByUserName(String userName);
}