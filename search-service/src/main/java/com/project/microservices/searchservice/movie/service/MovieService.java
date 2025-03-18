package com.project.microservices.searchservice.movie.service;

import java.util.List;

import com.project.microservices.searchservice.model.SearchQueryResponse;

import jakarta.validation.constraints.NotBlank;

public interface MovieService {
	
	public List<SearchQueryResponse> findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity);

	public List<String> findByMovieName(String movieName);

	public List<String> findMoviesByCity(String cityName);

	
}
