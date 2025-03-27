package com.project.microservices.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private Integer userId;
	private String userName;
	private String userEmail;
	private boolean success;
	private String message;
}
