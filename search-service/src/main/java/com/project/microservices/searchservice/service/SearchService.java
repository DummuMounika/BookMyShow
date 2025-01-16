package com.project.microservices.searchservice.service;

import java.util.List;

import com.project.microservices.searchservice.model.SearchResponse;

public interface SearchService {

	public SearchResponse findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity);

	public List<String> getAllCities();


}
