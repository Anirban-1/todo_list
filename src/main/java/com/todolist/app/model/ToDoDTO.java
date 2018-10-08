package com.todolist.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDTO extends CrudRepository<ToDo, Integer> {
	ToDo findByUserId(int userId);
}
