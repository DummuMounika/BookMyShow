package com.example.theaterservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.theaterservice.model.Theater;
import com.example.theaterservice.service.TheaterService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
public class TheaterController {
	
	private TheaterService theaterService;

	@Autowired
	public TheaterController(TheaterService theaterService) {
		super();
		this.theaterService = theaterService;
	}
	
	@GetMapping("/theaters")
	public ResponseEntity<List<Theater>> getAllTheaterDetails() {
		return new ResponseEntity<>(theaterService.getAllTheater(), HttpStatus.OK);
	}
	
	@PostMapping("/theaters")
	public ResponseEntity<Theater> createTheater(@RequestBody @Valid Theater theater) {
		return new ResponseEntity<>(theaterService.createTheater(theater), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/theaters/{theaterId}")
	public ResponseEntity<String> deleteTheater(@PathVariable @Valid @NotNull Integer theaterId){
		return new ResponseEntity<>(theaterService.deleteTheater(theaterId),HttpStatus.OK);
	}
	
	@PutMapping("/theaters/{theaterId}")
	public ResponseEntity<Theater> updateTheater(@RequestBody @Valid Theater theater,@PathVariable @Valid @NotNull  Integer theaterId){
		return new ResponseEntity<>(theaterService.updateTheater(theater, theaterId),HttpStatus.OK);
	}
	
	@GetMapping("/findTheaterById")
	public ResponseEntity<Theater> getSingleTheaterDetail(@RequestParam  @Valid @NotNull Integer id) {
		return new ResponseEntity<>(theaterService.getSingleTheater(id), HttpStatus.OK);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	
	@GetMapping("/findTheaterByName")
	public ResponseEntity<Theater> getTheaterDetailByName(@RequestParam @Valid @NotNull @NotBlank String name) {
		return new ResponseEntity<>(theaterService.getTheaterDetailByName(name),HttpStatus.OK);
	}

}
