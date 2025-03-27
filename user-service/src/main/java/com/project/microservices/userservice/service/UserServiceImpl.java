package com.project.microservices.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.userservice.exception.InvalidUserResponseException;
import com.project.microservices.userservice.exception.NotFoundException;
import com.project.microservices.userservice.model.User;
import com.project.microservices.userservice.model.UserRequest;
import com.project.microservices.userservice.model.UserResponse;
import com.project.microservices.userservice.model.entity.UserEntity;
import com.project.microservices.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
    
	//convertMethods
	private User convertUserEntityToUser(UserEntity userEntity) {
		return new User(userEntity.getUserId(),userEntity.getUserName(),userEntity.getUserEmail(),
				userEntity.getUserPassword(),userEntity.getUserNotificationtype(),userEntity.getUserCreatedon(),userEntity.getUserCreatedon());
	}
	
	private UserEntity convertUserToUserEntity(User user) {
		return new UserEntity(user.getUserId(),user.getUserName(),user.getUserEmail(),
			user.getUserPassword(),	user.getUserNotificationtype(),user.getUserCreatedon(),user.getUserCreatedon());
	}
	
	@Override
	public User createUser(User user) {
		try {
			UserEntity userEntity = convertUserToUserEntity(user);
			log.info("Creating User:" +userEntity.toString());
			UserEntity newUser = userRepository.save(userEntity);
			return convertUserEntityToUser(newUser);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Failed to create user: "+e.getMessage());		
		}
	}

	@Override
	public UserResponse loginUser(UserRequest userRequest) {
		Optional<UserEntity> userEntity = userRepository.findByUserEmail(userRequest.getUserEmail());
		if(userEntity.isEmpty()) {
			throw new InvalidUserResponseException(false, "Invalid credentials");
        }	
		UserEntity entity = userEntity.get();
		if(entity.getUserPassword().equals(userRequest.getUserPassword())) {
	         return new UserResponse(entity.getUserId(),entity.getUserName(),entity.getUserEmail(),true, "Login successful");
		} else {		
			throw new NotFoundException(false, "Passowrd was incorrect");		
		}
	}

	@Override
	public User getUserDetails(Integer userId) {
		Optional<UserEntity> userDetail = userRepository.findById(userId);
		if(userDetail.isEmpty()) {
			throw new NotFoundException(false, "User not found with ID");	
		}
		log.info("user found with {}",userId);
		
		return convertUserEntityToUser(userDetail.get());
	}


}
