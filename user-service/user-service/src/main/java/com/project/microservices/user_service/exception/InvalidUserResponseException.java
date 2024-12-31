package com.project.microservices.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidUserResponseException extends RuntimeException {

	public InvalidUserResponseException(boolean b, String string) {
	}

}
