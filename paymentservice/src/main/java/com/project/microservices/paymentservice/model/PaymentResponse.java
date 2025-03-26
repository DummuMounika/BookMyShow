package com.project.microservices.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	
	private User user;
	private Integer bookingId; 
	private Integer paymentId;
	private Integer paymentType;
	private boolean paymentStatus;
	private ShowDetails showDetails;
	private SeatPricingDetails seatPricingDetails;
	
	

}
