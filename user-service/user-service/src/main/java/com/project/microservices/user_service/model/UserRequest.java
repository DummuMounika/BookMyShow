package com.project.microservices.user_service.model;

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
		private String email;
		@NotEmpty @NotNull
		private String password;
}
