package com.example.theaterservice.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
	
	private int theaterId;
	@NotEmpty @NotNull (message="The Theater name is required")
	private String name;
	@NotEmpty @NotNull (message="The Theater location is required")
	private String address;
	@NotEmpty @NotNull
	private String city;
	@Positive
	private int totalSeats;

}
