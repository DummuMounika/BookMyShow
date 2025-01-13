package com.project.microservices.searchservice.movie.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.show.entity.ShowEntity;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movies")
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
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<ShowEntity> shows;

}