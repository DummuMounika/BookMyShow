package com.project.microservices.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.project.microservices.user_service.model.UserResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({InvalidUserResponseException.class})
	public ResponseEntity<UserResponse> handleInvalidUserResponseException(InvalidUserResponseException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse(false,"Invalid Credential"));
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<UserResponse> handleUserNotFoundException(UserNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse(false,"User Not Found"));
	}

}
