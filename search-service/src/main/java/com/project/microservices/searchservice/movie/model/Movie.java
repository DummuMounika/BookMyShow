package com.project.microservices.searchservice.movie.model;

import java.time.Duration;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	private int movieId;
	private String title;
	private String genre;
	private String language;
	private Duration duration;
	private LocalDate releaseDate;

}
