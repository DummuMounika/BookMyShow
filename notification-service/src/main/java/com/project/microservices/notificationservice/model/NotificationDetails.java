package com.project.microservices.notificationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.microservices.notificationservice.utils.StringObjectDeserializer;

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
	private String paymentStatus;
	
	private String movieName;
	private String theaterName; 
	private String showTime; 
	private String showDate;
	
	@JsonDeserialize(using = StringObjectDeserializer.class)
	private String selectedSeats;
	private Integer totalSeats;
	private Integer seatsPrize;
	
	
	private float convenienceFees;
	private float subTotalPrize;
}
