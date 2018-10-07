package com.todolist.app.model;

import org.springframework.data.repository.CrudRepository;

public interface ToDoDTO extends CrudRepository<ToDo, Integer> {
	ToDo findByUserId(int userId);
}
