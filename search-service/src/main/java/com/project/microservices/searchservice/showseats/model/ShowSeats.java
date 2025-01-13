package com.project.microservices.searchservice.showseats.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.model.Status;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeats {
	
	private Integer showseatId;
	private Integer showseatShowId;
	private String showseatRow;
	private Integer showseatSeatno;
	private Status showseatStatus;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showseatCreatedon;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showseatUpdatedon;
}
