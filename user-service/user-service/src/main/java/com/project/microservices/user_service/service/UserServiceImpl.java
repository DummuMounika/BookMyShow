package com.project.microservices.user_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.project.microservices.user_service.exception.InvalidUserResponseException;
import com.project.microservices.user_service.exception.UserNotFoundException;
import com.project.microservices.user_service.mapper.UserMapper;
import com.project.microservices.user_service.model.User;
import com.project.microservices.user_service.model.UserRequest;
import com.project.microservices.user_service.model.UserResponse;
import com.project.microservices.user_service.model.entity.UserEntity;
import com.project.microservices.user_service.repository.UserRepository;

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
		return new User(userEntity.getId(),userEntity.getName(),userEntity.getEmail(),
				userEntity.getPassword(),userEntity.getCreatedon(),userEntity.getUpdatedon());
	}
	
	private UserEntity convertUserToUserEntity(User user) {
		return new UserEntity(user.getId(),user.getName(),user.getEmail(),
				user.getPassword(),user.getCreatedon(),user.getUpdatedon());
	}
	
	@Override
	public User createUser(User user) {
		try {
			//UserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
			UserEntity userEntity = convertUserToUserEntity(user);
			log.info("Creating User:" +userEntity.toString());
			UserEntity newUser = userRepository.save(userEntity);
			//return UserMapper.INSTANCE.toModel(newUser);
			return convertUserEntityToUser(newUser);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Failed to create user: "+e.getMessage());		
		}
	}

	@Override
	public UserResponse loginUser(UserRequest userRequest) {
		Optional<UserEntity> userEntity = userRepository.findByEmail(userRequest.getEmail());
		if(userEntity.isPresent()) {
			UserEntity entity = userEntity.get();
			 if (entity.getPassword().equals(userRequest.getPassword())) {
	              return new UserResponse(true, "Login successful");
	         } else {
	        	  throw new InvalidUserResponseException(false, "Invalid credentials");
	         }	
		}else {		
			throw new UserNotFoundException(false, "User not found");		
		}
	}


}
