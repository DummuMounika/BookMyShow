package com.project.microservices.bookingservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingSummaryRequest {
	
	private List<Integer> seatsUniqueIds;
	private Integer showId;
}
