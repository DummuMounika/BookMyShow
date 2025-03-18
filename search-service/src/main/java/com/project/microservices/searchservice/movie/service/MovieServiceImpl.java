package com.project.microservices.searchservice.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.searchservice.exception.MovieNameNotFoundException;
import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.movie.repository.MovieRepository;
import com.project.microservices.searchservice.show.repository.ShowRepository;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
	
	
	private MovieRepository movieRepository;
	private ShowRepository showRepository;
	Integer i = 1;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository, ShowRepository showRepository) {
		super();
		this.movieRepository = movieRepository;
		this.showRepository = showRepository;
	}	

	@Override
	public List<SearchQueryResponse> findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity) {
		return movieRepository.searchByMovieNameAndCity(movieName, theaterCity);
	}

	@Override
	@RateLimiter(name = "myRateLimiter")
	public List<String> findByMovieName(String movieName) {
		log.info("Opened this movie serach service for {} th", i++);
		List<String> listOfMovieNames = movieRepository.findByMovieName(movieName);
		if(listOfMovieNames.isEmpty()) {
			log.warn("No movie search list found for movieName: {}", movieName);
		    throw new MovieNameNotFoundException("No list found for the given name: " + movieName);
		}
		return listOfMovieNames;
	}

	@Override
	public List<String> findMoviesByCity(String cityName) {
		List<String> listOfMovieNamesByCity = showRepository.searchByCitiesToGetMovies(cityName);
		if(listOfMovieNamesByCity.isEmpty()) {
			log.warn("No movie search list found for movieName: {}", cityName);
		    throw new MovieNameNotFoundException("No list found for the given name: " + cityName);
		}
		return listOfMovieNamesByCity;
	}

}
