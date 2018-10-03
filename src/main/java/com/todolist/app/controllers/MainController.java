package com.todolist.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.app.model.*;
import com.todolist.app.service.*;

@RestController
@RequestMapping("/todo")
public class MainController {

	@Autowired
	UserService userService;
	
    @PostMapping("/add")
    public User greeting(@RequestBody User user) {
    	
    	userService.saveUser(user);
    	return user;
    }
    
    @GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
    	return userService.findAllUsers();
	}
}
