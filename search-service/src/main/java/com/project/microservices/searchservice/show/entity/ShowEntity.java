package com.project.microservices.searchservice.show.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalTime;

import com.project.microservices.searchservice.movie.entity.MovieEntity;
import com.project.microservices.searchservice.theater.entity.TheaterEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="show")
public class ShowEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int showId;
	
	private LocalDate showDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	private MovieEntity movie;
	
	@ManyToOne
	@JoinColumn(name="theaterId")
	private TheaterEntity theater;


}
