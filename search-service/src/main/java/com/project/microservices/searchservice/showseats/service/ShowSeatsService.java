package com.project.microservices.searchservice.showseats.service;

import com.project.microservices.searchservice.showseats.model.ShowSeatsResponse;

public interface ShowSeatsService {
		
	public ShowSeatsResponse findShowSeatDetails(int showId);
}
