package com.project.microservices.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.microservices.paymentservice.model.BookingDetails;
import com.project.microservices.paymentservice.model.BookingSummaryRequest;
import com.project.microservices.paymentservice.model.BookingSummaryResponse;

@FeignClient(name="booking-service")
public interface BookingServiceProxy {
	
	@PostMapping("api/booking/summary")
	public ResponseEntity<BookingSummaryResponse> getbookingSummary(@RequestBody BookingSummaryRequest bookingSummaryRequest);
	
	@PostMapping("api/addBooking")
	public ResponseEntity<Integer> createBookingDetails(@RequestBody BookingDetails bookingTableRequest);
}
