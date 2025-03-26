package com.project.microservices.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeats {
	
	private Integer showseatShowId;
	private String showseatRow;
	private Integer showseatSeatno;
}
