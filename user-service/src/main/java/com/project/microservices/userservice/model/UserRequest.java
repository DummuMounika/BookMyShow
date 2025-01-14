package com.project.microservices.userservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
		@NotEmpty @NotNull
		private String userEmail;
		@NotEmpty @NotNull
		private String userPassword;
}
