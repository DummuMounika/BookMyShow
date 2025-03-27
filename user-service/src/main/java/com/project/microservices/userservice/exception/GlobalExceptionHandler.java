package com.project.microservices.userservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.project.microservices.userservice.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({InvalidUserResponseException.class})
	public ResponseEntity<Error> handleInvalidUserResponseException(InvalidUserResponseException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(false,"Invalid Credential"));
	}
	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<Error> handleUserNotFoundException(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(false,"User Not Found"));
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
	
	 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ResponseEntity<?> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
	        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type is '%s'.",
	                ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());
	        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	    }

}
