package com.project.microservices.adminservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowReqBody {
	
	private Integer showTheaterId;
	private Integer showMovieId;
	private LocalTime showStarttime; 
	private LocalTime showEndtime;  
	private LocalDate showDate;

}
