package com.project.microservices.searchservice.show.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.searchservice.model.Status;
import com.project.microservices.searchservice.movie.entity.MovieEntity;
import com.project.microservices.searchservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.searchservice.theater.entity.TheaterEntity;
import com.project.microservices.searchservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "shows")
public class ShowEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shows_show_id_seq")
	@SequenceGenerator(name ="shows_show_id_seq", sequenceName="shows_show_id_seq", allocationSize=1)
	@Column(name="show_id")
	private Integer showId;

	@Column(name="show_date")
	private LocalDate showDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_starttime")
	private Timestamp showStarttime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_endtime")
	private Timestamp showEndtime;
	
	@Column(name="show_theater_id")
	private Integer showTheaterId;

	@Column(name="show_movie_id")
	private Integer showMovieId;
	
	@ManyToOne
	@JoinColumn(name = "show_theater_id",  nullable = false,insertable=false, updatable=false)
	private TheaterEntity theater;
	
	@ManyToOne
	@JoinColumn(name = "show_movie_id",  nullable = false,insertable=false, updatable=false)
	private MovieEntity movie;

	@Enumerated(EnumType.STRING)
	@Column(name="show_status")
	private Status showStatus;
	
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowSeatsEntity> showSeats;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_createdon")
	private Timestamp showCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_updatedon")
	private Timestamp showUpdatedon;
	
}
