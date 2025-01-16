package com.project.microservices.searchservice.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.searchservice.exception.MovieNameNotFoundException;
import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.movie.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
	
	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}	

	@Override
	public List<SearchQueryResponse> findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity) {
		return movieRepository.searchByMovieNameAndCity(movieName, theaterCity);
	}

	@Override
	public List<String> findByMovieName(String movieName) {
		List<String> listOfMovieNames = movieRepository.findByMovieName(movieName);
		if(listOfMovieNames.isEmpty()) {
			log.warn("No movie search list found for movieName: {}", movieName);
		    throw new MovieNameNotFoundException("No list found for the given name: " + movieName);
		}
		return listOfMovieNames;
	}

}
