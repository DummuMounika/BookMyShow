package com.project.microservices.searchservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
	
	private int movieId;
	private String movieName;
	private String theaterCity;
	private List<TheaterShows> theaterShows;
}
