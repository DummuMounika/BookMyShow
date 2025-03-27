package com.project.microservices.searchservice.service;

import java.util.List;

import com.project.microservices.searchservice.model.SearchResponse;

import jakarta.validation.constraints.NotBlank;

public interface SearchService {

	public SearchResponse findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity);

	public SearchResponse findTheatersByMovieIdAndTheaterCityId(Integer movieId, Integer theaterCityId);
	
	public List<String> getAllCities();
	
	public List<String> getAllCities1();

	

}
