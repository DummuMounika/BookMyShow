package com.project.microservices.searchservice.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.movie.service.MovieService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
public class MovieController {
	
private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@GetMapping("/api/movie/search")
	public ResponseEntity<List<String>> getMoviesByName(@RequestParam @NotBlank String movieName) {
	    return new ResponseEntity<>(movieService.findByMovieName(movieName), HttpStatus.OK);
	}
	
	@GetMapping("/api/movie/explore")
	public ResponseEntity<List<String>> getMoviesByCity(@RequestParam @NotBlank String cityName) {
	    return new ResponseEntity<>(movieService.findMoviesByCity(cityName), HttpStatus.OK);
	}
	
	@GetMapping("/api/v2/movie/explore")
	public ResponseEntity<Map<Integer,String>> getMoviesByCityId(@RequestParam @NotNull Integer cityId) {
	    return new ResponseEntity<>(movieService.findMoviesByCityId(cityId), HttpStatus.OK);
	}
	

}
