package com.project.microservices.searchservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.model.SearchResponse;
import com.project.microservices.searchservice.service.SearchService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
public class SearchController {
	
	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		super();
		this.searchService = searchService;
	}

	@GetMapping("/api/shows/search")
	public ResponseEntity<SearchResponse> getShows(@RequestParam @NotBlank String movieName,@RequestParam @NotBlank String theaterCity) {
		SearchResponse result = searchService.findTheatersByMovieNameAndTheaterCity(movieName, theaterCity);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/api/v2/shows/search")
	public ResponseEntity<SearchResponse> getShows1(@RequestParam @NotNull Integer movieId,@RequestParam @NotNull Integer theaterCityId) {
		SearchResponse result = searchService.findTheatersByMovieIdAndTheaterCityId(movieId, theaterCityId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/api/cities")
	public ResponseEntity<List<String>> getCities(){
		return new ResponseEntity<>(searchService.getAllCities(),HttpStatus.OK);
	}
	
	@GetMapping("/api/v2/cities")
	public ResponseEntity<HashMap<Integer, String>> getCities1(){
		return new ResponseEntity<>(searchService.getAllCities1(),HttpStatus.OK);
	}
	

}
