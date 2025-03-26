package com.project.microservices.adminservice.movie.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="movies_movie_id_seq")
	@SequenceGenerator(name ="movies_movie_id_seq", sequenceName="movies_movie_id_seq", allocationSize=1)
	@Column(name="movie_id")
	private Integer movieId;

	@Column(name="movie_name")
	private String movieName;

	@Column(name="movie_language")
	private String movieLanguage;

	@Column(name="movie_genre")
	private String movieGenre;

	@Column(name="movie_duration")
	private Duration movieDuration;

	@Column(name="movie_releasedate")
	private Date movieReleasedate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="movie_createdon")
	private Timestamp movieCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="movie_updatedon")
	private Timestamp movieUpdatedon;
}