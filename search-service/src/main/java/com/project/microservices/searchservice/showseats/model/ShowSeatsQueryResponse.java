package com.project.microservices.searchservice.showseats.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.project.microservices.searchservice.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatsQueryResponse {
	
	private Integer movieId;
	private String movieName;
	private Integer theaterId;
	private String theaterName; 
	private Integer showId;
	private Timestamp showStarttime; 
	private LocalDate showDate;
	private Integer showseatId;
	private String showseatRow;
	private Integer showseatSeatno;
	private Status showseatStatus;
	private Integer showseatSeatTicketCost;

}
