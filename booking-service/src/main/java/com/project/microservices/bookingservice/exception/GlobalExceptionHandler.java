package com.project.microservices.bookingservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.project.microservices.bookingservice.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ ShowDetailsFetchException.class })
	public ResponseEntity<?> handleMovieDetailsFetchException(ShowDetailsFetchException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage(), "Failed"));
	}

	@ExceptionHandler({ SeatsUnavailableException.class })
	public ResponseEntity<?> handleSeatStatusAvailableException(SeatsUnavailableException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage(), "Failed"));
	}

	@ExceptionHandler({ InvalidSeatIdException.class })
	public ResponseEntity<?> handleShowSeatIdException(InvalidSeatIdException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(exception.getMessage(), "Failed"));
	}

	@ExceptionHandler({ InvalidShowIdException.class })
	public ResponseEntity<?> handleShowSeatShowIdException(InvalidShowIdException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(exception.getMessage(), "Failed"));
	}
	
	@ExceptionHandler({InvalidRequestBodyException.class })
	public ResponseEntity<?> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(exception.getMessage(), "Failed"));
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
