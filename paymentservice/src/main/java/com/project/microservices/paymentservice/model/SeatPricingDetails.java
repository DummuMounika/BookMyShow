package com.project.microservices.paymentservice.model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatPricingDetails {
	
	private HashMap<String, Integer> selectedSeats;
	private Integer totalSeats;
	private Integer seatsPrize;
	private float convenienceFees;
	private float subTotalPrize;
}