package com.project.microservices.searchservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.model.SearchResponse;
import com.project.microservices.searchservice.service.SearchService;

import jakarta.validation.constraints.NotBlank;

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
	    return new ResponseEntity<>(searchService.findTheatersByMovieNameAndTheaterCity(movieName, theaterCity), HttpStatus.OK);
	}
	
	@GetMapping("/api/cities")
	public ResponseEntity<List<String>> getCities(){
		return new ResponseEntity<>(searchService.getAllCities(),HttpStatus.OK);
	}
	

}
