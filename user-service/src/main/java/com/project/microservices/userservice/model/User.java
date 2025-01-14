package com.project.microservices.userservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.userservice.utils.JsonTimestampSerializer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Integer userId;
	@NotEmpty @NotNull
	private String userName;
	@NotEmpty @NotNull
	private String userEmail;
	@NotEmpty @NotNull
	private String userPassword;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp userCreatedon;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp userUpdatedon;

}
