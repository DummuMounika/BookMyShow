package com.project.microservices.searchservice.movie.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.project.microservices.searchservice.show.entity.ShowEntity;
import com.project.microservices.searchservice.show.model.Show;
import com.project.microservices.searchservice.theater.entity.TheaterEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie")
public class MovieEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int movieId;
	@Column(name="name")
	private String title;
	private String genre;
	private String language;
	private Duration duration;
	private LocalDate releaseDate;
	
	@OneToMany(mappedBy = "movie")
	private List<ShowEntity> showtimes;

}
