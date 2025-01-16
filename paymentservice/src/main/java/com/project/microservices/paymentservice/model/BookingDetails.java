package com.project.microservices.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
	
	private SeatPricingDetails seatPricingDetails;
	private Integer userId;
	private Integer showId;
	private Integer paymentId;
	private boolean paymentStatus;

}
