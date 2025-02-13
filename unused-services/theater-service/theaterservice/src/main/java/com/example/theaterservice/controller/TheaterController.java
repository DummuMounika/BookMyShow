package com.example.theaterservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.theaterservice.model.Theater;
import com.example.theaterservice.service.TheaterService;

import jakarta.validation.Valid;

@RestController
public class TheaterController {
	
	private TheaterService theaterService;

	@Autowired
	public TheaterController(TheaterService theaterService) {
		super();
		this.theaterService = theaterService;
	}
	
	
	@PostMapping("api/theaters")
	public ResponseEntity<Theater> createTheater(@RequestBody @Valid Theater theater) {
		return new ResponseEntity<>(theaterService.createTheater(theater), HttpStatus.CREATED);
	}
	
}
