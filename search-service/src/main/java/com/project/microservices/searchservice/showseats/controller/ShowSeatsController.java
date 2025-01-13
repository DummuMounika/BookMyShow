package com.project.microservices.searchservice.showseats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.searchservice.showseats.model.ShowSeatsResponse;
import com.project.microservices.searchservice.showseats.service.ShowSeatsService;

import jakarta.validation.constraints.NotNull;

@RestController
public class ShowSeatsController {
	
	private ShowSeatsService showSeatsService;
	
	@Autowired
	public ShowSeatsController(ShowSeatsService showSeatsService) {
		super();
		this.showSeatsService = showSeatsService;
	}

	@GetMapping("/api/theater/seats")
	public ResponseEntity<ShowSeatsResponse> getShowSeatDetails(@RequestParam @NotNull Integer showId){
		return new ResponseEntity<>(showSeatsService.findShowSeatDetails(showId), HttpStatus.OK);	
	}

}
