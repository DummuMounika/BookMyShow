package com.project.microservices.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.user_service.model.User;
import com.project.microservices.user_service.model.UserRequest;
import com.project.microservices.user_service.model.UserResponse;
import com.project.microservices.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/api/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.loginUser(userRequest), HttpStatus.OK);
	}
	
}
