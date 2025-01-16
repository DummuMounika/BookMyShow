package com.project.microservices.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.bookingservice.model.BookingSummaryRequest;
import com.project.microservices.bookingservice.model.BookingSummaryResponse;
import com.project.microservices.bookingservice.model.BookingDetails;
import com.project.microservices.bookingservice.services.BookingService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RestController
public class BookingController {
	
	private BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}

	@PostMapping("api/booking/summary")
	public ResponseEntity<BookingSummaryResponse> getbookingSummary(@RequestBody BookingSummaryRequest bookingSummaryRequest){
	     return ResponseEntity.ok( bookingService.generateBookingSummary(bookingSummaryRequest));
	}
	
	@PatchMapping("api/booking/changeseatstatus")
	public ResponseEntity<String> changeSeatStatus(
	        @RequestParam @NotNull @Size(min = 1) List<Integer> seatUniqueIds,
	        @RequestParam @NotNull Integer status,
	        @RequestParam @NotNull Integer showId){
		return ResponseEntity.ok(bookingService.updateSeatStatus(seatUniqueIds,status,showId));
	}
	
	@PostMapping("api/addBooking")
	public ResponseEntity<Integer> createBookingDetails(@RequestBody BookingDetails bookingDetails){
		return ResponseEntity.ok(bookingService.createBooking(bookingDetails));
	}
	

}
