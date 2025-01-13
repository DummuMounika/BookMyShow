package com.project.microservices.searchservice.movie.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	
	private int movieId;
	private String movieName;
	private String movieLanguage;
	private String movieGenre;
	private Duration movieDuration;
	private Date movieReleasedate;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp movieCreatedon;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp movieUpdatedon;

}