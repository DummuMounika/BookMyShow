package com.project.microservices.userservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private Integer userId;
	@NotEmpty @NotNull
	private String userName;
	@NotEmpty @NotNull
	private String userEmail;
	@NotEmpty @NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String userPassword;
	private Integer userNotificationtype = 1;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp userCreatedon;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp userUpdatedon;

}
