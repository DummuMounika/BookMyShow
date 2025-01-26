package com.project.microservices.userservice.service;

import com.project.microservices.userservice.model.User;
import com.project.microservices.userservice.model.UserRequest;
import com.project.microservices.userservice.model.UserResponse;

public interface UserService {
	
	public User createUser(User user);
	public UserResponse loginUser(UserRequest userRequest);
	public User getUserDetails(Integer userId);
}
