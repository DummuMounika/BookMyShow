package com.project.microservices.searchservice.service;

import java.util.HashMap;
import java.util.List;

import com.project.microservices.searchservice.model.SearchResponse;

public interface SearchService {

	public SearchResponse findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity);

	public SearchResponse findTheatersByMovieIdAndTheaterCityId(Integer movieId, Integer theaterCityId);
	
	public List<String> getAllCities();
	
	public HashMap<Integer, String> getAllCities1();

	

}
