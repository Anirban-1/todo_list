package com.todolist.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.app.model.*;
import com.todolist.app.service.*;

@RestController
@RequestMapping("/todo")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService userService;

	@Autowired
	ToDoService todoService;

	@PostMapping("/signup")
	public User greeting(@RequestBody User user) {

		userService.saveUser(user);
		return user;
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Void> login(@RequestBody User user) {

		User entity = userService.findByUserName(user.getUserName());

		log.info("Trying to login");
		if (entity != null && entity.getPassword().equals(user.getPassword())) {
			log.info("Entity found");
			return new ResponseEntity<>(HttpStatus.FOUND);
		}

		log.info("Entity not found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public @ResponseBody ResponseEntity<Void> addTodo(@RequestBody UserTodo userTodo) {
		final String userName = userTodo.getUserName();
		final String todo = userTodo.getTodo();

		User user = userService.findByUserName(userName);
		if (user != null && todo != null) {
			todoService.saveToDo(todo, user.getId());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);
	}

	@DeleteMapping("/deletetask")
	public @ResponseBody ResponseEntity<Void> deleteTodo(@RequestBody ToDo todo) {
		if (todo != null && todo.getId() > 0) {
			todoService.deleteTodo(todo.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updatetask")
	public @ResponseBody ResponseEntity<Void> updateTask(@RequestBody ToDo todo) {
		if (todo != null) {
			todoService.updateTodo(todo.getId(), todo.getTask());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.findAllUsers();
	}
}
