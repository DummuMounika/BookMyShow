package com.project.microservices.searchservice.theater.model;

import java.util.List;

import com.project.microservices.searchservice.show.model.ShowDetails;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDetails {
	
	private int theaterId;
	@NotEmpty @NotNull 
	private String theaterName;
	@NotEmpty @NotNull
	private String theaterCity;
	//private HashMap<Date, List<ShowEntity>> shows;
	private List<ShowDetails> showTime;
}
