package com.project.microservices.searchservice.movie.service;

import java.util.List;

import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.model.SearchQueryResponse1;

public interface MovieService {
	
	public List<SearchQueryResponse> findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity);
	
	public List<SearchQueryResponse1> findTheatersByMovieIdAndTheaterId(Integer movieId, Integer theaterId);

	public List<String> findByMovieName(String movieName);

	public List<String> findMoviesByCity(String cityName);

	public List<String> findMoviesByCityId(Integer cityId);

	
}
