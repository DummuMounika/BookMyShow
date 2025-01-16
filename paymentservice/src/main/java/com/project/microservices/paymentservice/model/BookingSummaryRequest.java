package com.project.microservices.paymentservice.model;

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
	private Status currentSeatStatus;
	private Boolean isUpdateRequired;
	
	/**
	 * 1. status: 0 -  isUpdateRequired -  true
	 * 2.status: 2 - isUpdateRequired -  false
	 */
}
