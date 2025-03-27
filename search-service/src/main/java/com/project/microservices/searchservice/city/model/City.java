package com.project.microservices.searchservice.city.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
	
	@Column(name="city_id")
	private Integer cityId;
		
	@Column(name="city_name")
	private String cityName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="city_createdon")
	private Timestamp cityCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="city_updatedon")
	private Timestamp cityUpdatedon;}

