package com.project.microservices.adminservice.exception;

import java.util.HashMap;
import java.util.Map;
import com.project.microservices.adminservice.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ ShowSeatsDetailsFetchException.class })
	public ResponseEntity<?> handleMovieDetailsFetchException(ShowSeatsDetailsFetchException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage(), "Failed"));
	}

	@ExceptionHandler({ ShowServiceUnavailableException.class })
	public ResponseEntity<?> handleSeatStatusAvailableException(ShowServiceUnavailableException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage(), "Failed"));
	}

	@ExceptionHandler({ InvalidIdException.class })
	public ResponseEntity<?> handleShowSeatIdException(InvalidIdException exception) {
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
