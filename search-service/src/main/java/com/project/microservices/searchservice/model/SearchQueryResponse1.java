package com.project.microservices.searchservice.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchQueryResponse1 {
	
	private int movieId;
	private String movieName;
	private int theaterId;
	private String theaterName;
	//private String theaterCity;
	private Integer theaterCityId;
	//private String cityName;
	private LocalDate showDate;
	private Timestamp showStarttime;
	private int showId;

}
