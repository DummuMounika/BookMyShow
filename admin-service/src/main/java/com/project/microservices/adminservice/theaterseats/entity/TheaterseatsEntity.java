package com.project.microservices.adminservice.theaterseats.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.show.entity.ShowEntity;
import com.project.microservices.adminservice.theater.entity.TheaterEntity;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theaterseats")  //TODO add cost column
public class TheaterseatsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="theaterseats_theaterseat_id_seq")
	@SequenceGenerator(name ="theaterseats_theaterseat_id_seq", sequenceName="theaterseats_theaterseat_id_seq", allocationSize=1)
	@Column(name="theaterseat_id")
	private Integer theaterseatId;

	@Column(name="theaterseat_row")
	private String theaterseatRow;

	@Column(name="theaterseat_noofseats")
	private Integer theaterseatNoofseats;

	@Column(name="theaterseat_theater_id")
	private Integer theaterseatTheaterId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theaterseat_createdon")
	private Timestamp theaterseatCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theaterseat_updatedon")
	private Timestamp theaterseatUpdatedon;
}
