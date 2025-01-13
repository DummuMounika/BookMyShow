package com.project.microservices.searchservice.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.searchservice.exception.InvalidMovieNameException;
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
		List<String> listOfMovies = new ArrayList<>();
		List<String> searchList = movieRepository.findByMovieName(movieName);
		if(searchList.isEmpty()) {
			log.warn("No movie search list found for movieName: {}", movieName);
		    throw new InvalidMovieNameException("No list found for the given name: " + movieName);
		}
		for(String movie : searchList ) {
			listOfMovies.add(movie);
		}
		return listOfMovies;
	}

}
