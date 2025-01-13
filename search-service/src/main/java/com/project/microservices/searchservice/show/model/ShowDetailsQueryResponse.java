package com.project.microservices.searchservice.show.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDetailsQueryResponse {
	
	private Integer movieId;
	private String movieName;
	private Integer theaterId;
	private String theaterName; 
	private Integer showId;
	private Timestamp showTime; 
	private LocalDate showDate;

}
