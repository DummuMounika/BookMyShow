package com.project.microservices.searchservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQueryResponse {
	
	private int id;
	private String movieName;
	private int theaterId;
	private String theaterName;
	private String theaterCity;
	private LocalDate showDate;
	private LocalTime startTime;

}
