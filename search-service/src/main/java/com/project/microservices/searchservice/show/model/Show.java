package com.project.microservices.searchservice.show.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.model.Status;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
	
	private Integer showId;
	private LocalDate showDate;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showStarttime;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showEndtime;
	private Integer showTheaterId;
	private Integer showMovieId;
	private Status showStatus;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showCreatedon;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp showUpdatedon;
}
