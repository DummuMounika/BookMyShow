package com.project.microservices.adminservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShowServiceUnavailableException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShowServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

}
