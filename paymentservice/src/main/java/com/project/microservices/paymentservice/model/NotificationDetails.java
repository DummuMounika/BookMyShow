package com.project.microservices.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDetails {
	
	private String userName;
	private String userEmail;
	
	private Integer bookingId; 
	private Integer paymentId;
	private String paymentType;
	private boolean paymentStatus;
	
	private String movieName;
	private String theaterName; 
	private String showTime; 
	private String showDate;

	private String selectedSeats;
	private Integer totalSeats;
	private Integer seatsPrize;
	
	
	private float convenienceFees;
	private float subTotalPrize;
}
