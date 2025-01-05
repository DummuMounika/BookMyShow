package com.project.microservices.searchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.model.SearchResponse;
import com.project.microservices.searchservice.service.SearchService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class SearchController {
	
	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		super();
		this.searchService = searchService;
	}

	@GetMapping("/api/movie/search")
	public ResponseEntity<SearchResponse> searchMovie(@Valid @RequestParam  @NotNull String name,  @NotNull String city) {
		return new ResponseEntity<>(searchService.findMovieByNameAndCity(name, city),HttpStatus.OK);
	}

}
