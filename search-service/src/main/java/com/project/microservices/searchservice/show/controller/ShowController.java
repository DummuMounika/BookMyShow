package com.project.microservices.searchservice.show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.show.model.ShowDetails;
import com.project.microservices.searchservice.show.service.ShowService;

import jakarta.validation.constraints.NotNull;

@RestController
public class ShowController {
	
	private ShowService showService;
	
	@Autowired
	public ShowController(ShowService showService) {
		super();
		this.showService = showService;
	}

	@GetMapping("/api/showdetails")
	public ResponseEntity<ShowDetails> searchShowDetails(@RequestParam @NotNull Integer showId) {
	    return new ResponseEntity<>(showService.findShowDetails(showId), HttpStatus.OK);
	}

}
