package com.project.microservices.searchservice.theater.model;


import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
	
	
	private Integer theaterId;

	@NotEmpty @NotNull (message="The Theater name is required")
	@Column(name="theater_name")
	private String theaterName;

	@NotEmpty @NotNull (message="The Theater location is required")
	@Column(name="theater_address")
	private String theaterAddress;

	@NotEmpty @NotNull
	@Column(name="theater_city")
	private String theaterCity;
	
	@Column(name="theater_city_id")
	private Integer theaterCityId;

	@Positive
	@Column(name="theater_totalseats")
	private Integer theaterTotalseats;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theater_createdon")
	private Timestamp theaterCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theater_updatedon")
	private Timestamp theaterUpdatedon;
	

}
