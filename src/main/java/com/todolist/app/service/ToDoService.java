package com.todolist.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.app.model.ToDo;
import com.todolist.app.model.ToDoDTO;
import com.todolist.app.model.User;
import com.todolist.app.model.UserDTO;

@Service
public class ToDoService {

	@Autowired
	ToDoDTO toDoDTO;

	@Autowired
	UserDTO userDTO;

	public List<ToDo> findByUserName(String userName) {
		Iterator<ToDo> toDoList = toDoDTO.findAll().iterator();
		ArrayList<ToDo> toDoCollection = new ArrayList<>();

		while (toDoList.hasNext()) {
			ToDo toDo = toDoList.next();
			if (toDo.getId() == userDTO.findByUserName(userName).getId()) {
				toDoCollection.add(toDo);
			}
		}

		return toDoCollection;
	}

	public void saveToDo(String toDo, Integer userId) {
		if (toDo != null && userId != null) {
			ToDo newTodo = new ToDo();
			newTodo.setTask(toDo);
			newTodo.setUserId(userId);
			toDoDTO.save(newTodo);
		}
	}
}
