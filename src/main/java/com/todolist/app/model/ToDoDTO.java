package com.todolist.app.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ToDoDTO extends CrudRepository<ToDo, Integer> {
	ToDo findByUserId(int userId);

	@Modifying
	@Query(value = "UPDATE to_do SET task = ?1 WHERE id = ?2", nativeQuery = true)
	void updateTodo(final String task, final int id);
}
