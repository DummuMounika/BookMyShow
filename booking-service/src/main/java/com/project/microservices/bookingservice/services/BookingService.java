package com.project.microservices.bookingservice.services;

import java.util.List;

import com.project.microservices.bookingservice.model.BookingSummaryRequest;
import com.project.microservices.bookingservice.model.BookingSummaryResponse;
import com.project.microservices.bookingservice.model.BookingDetails;
import com.project.microservices.bookingservice.model.ShowDetails;

public interface BookingService {
	
	public BookingSummaryResponse generateBookingSummary(BookingSummaryRequest bookingSummaryRequest);
	
	public ShowDetails fetchShowDetailsFromSearchService(Integer showId);
	
	public  String updateSeatStatus(List<Integer> seatUniqueId,Integer status,Integer showId);

	public Integer createBooking(BookingDetails bookingTableRequest);		
}
