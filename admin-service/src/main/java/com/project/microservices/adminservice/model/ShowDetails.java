package com.project.microservices.adminservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDetails {
	
	private Integer movieId;
	private String movieName;
	private Integer theaterId;
	private String theaterName; 
	private Integer showId;
	private String showTime; 
	private LocalDate showDate;

}
