package com.project.microservices.user_service.service;

import com.project.microservices.user_service.model.User;
import com.project.microservices.user_service.model.UserRequest;
import com.project.microservices.user_service.model.UserResponse;

public interface UserService {
	
	public User createUser(User user);
	public UserResponse loginUser(UserRequest userRequest);
}
