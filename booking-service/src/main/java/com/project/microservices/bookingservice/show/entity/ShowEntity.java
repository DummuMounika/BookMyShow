package com.project.microservices.bookingservice.show.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.bookingservice.model.Status;
import com.project.microservices.bookingservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.bookingservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Enumerated(EnumType.ORDINAL)
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
