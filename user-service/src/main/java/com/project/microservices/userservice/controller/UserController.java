package com.project.microservices.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.userservice.model.User;
import com.project.microservices.userservice.model.UserRequest;
import com.project.microservices.userservice.model.UserResponse;
import com.project.microservices.userservice.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
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
	
	@GetMapping("/api/userDetails")
	public ResponseEntity<User> userDetails(@RequestParam @NotNull Integer userId){
		return new ResponseEntity<>(userService.getUserDetails(userId),HttpStatus.OK);
	}
	
}
