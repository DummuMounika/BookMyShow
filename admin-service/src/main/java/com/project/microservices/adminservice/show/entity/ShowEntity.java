package com.project.microservices.adminservice.show.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.adminservice.theater.entity.TheaterEntity;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	//@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_starttime")
	private LocalTime showStarttime;

	//@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_endtime")
	private LocalTime showEndtime;
	
	@Column(name="show_theater_id")
	private Integer showTheaterId;

	@Column(name="show_movie_id")
	private Integer showMovieId;
	
	//@Enumerated(EnumType.ORDINAL)
	@Column(name = "show_status", nullable = false, insertable = false, updatable = true)
	private String showStatus;
	
	@ManyToOne
	@JoinColumn(name = "show_theater_id",  nullable = false,insertable=false, updatable=false)
	private TheaterEntity theater;
	
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowSeatsEntity> showSeats;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_createdon", insertable = false, updatable = true)
	private Timestamp showCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="show_updatedon", insertable = false, updatable = true)
	private Timestamp showUpdatedon;
	
}
