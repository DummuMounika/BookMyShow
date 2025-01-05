package com.project.microservices.userservice.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private int id;
	@NotEmpty @NotNull
	private String name;
	@NotEmpty @NotNull
	private String email;
	@NotEmpty @NotNull
	private String password;
	private LocalDate createdon;
	private LocalDate updatedon;

}
