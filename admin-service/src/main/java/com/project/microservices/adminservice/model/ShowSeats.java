package com.project.microservices.adminservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

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
