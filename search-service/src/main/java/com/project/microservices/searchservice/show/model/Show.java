package com.project.microservices.searchservice.show.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
	
	private int showId;
	private int theaterId;
	private int movieId;
	private String status;
	private LocalDate showDate;
	private LocalTime startTime;
	private LocalTime endTime;

}
