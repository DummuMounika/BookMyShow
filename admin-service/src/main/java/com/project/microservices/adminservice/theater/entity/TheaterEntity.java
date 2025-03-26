package com.project.microservices.adminservice.theater.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.show.entity.ShowEntity;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

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
@Table(name="theaters")
public class TheaterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="theaters_theater_id_seq")
	@SequenceGenerator(name ="theaters_theater_id_seq", sequenceName="theaters_theater_id_seq", allocationSize=1)
	@Column(name="theater_id")
	private Integer theaterId;

	@Column(name="theater_name")
	private String theaterName;

	@Column(name="theater_address")
	private String theaterAddress;

	@Column(name="theater_city")
	private String theaterCity;
	
	@Column(name="theater_city_id")
	private String theaterCityId;

	@Column(name="theater_totalseats")
	private Integer theaterTotalseats;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theater_createdon")
	private Timestamp theaterCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="theater_updatedon")
	private Timestamp theaterUpdatedon;
	
	@OneToMany(mappedBy = "theater")
    private List<ShowEntity> shows;
}