package com.project.microservices.movie_service.modelentity;

import java.time.Duration;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="movies")
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int movieId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private String language;
	
	@Column(nullable = false)
	private Duration duration;
	
	@Column(name = "release_date",nullable = false)
	private LocalDate releaseDate;

}
