package com.project.microservices.bookingservice.showseats.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.bookingservice.model.Status;
import com.project.microservices.bookingservice.show.entity.ShowEntity;
import com.project.microservices.bookingservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="showseats")
public class ShowSeatsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="showseats_showseat_id_seq")
	@SequenceGenerator(name ="showseats_showseat_id_seq", sequenceName="showseats_showseat_id_seq", allocationSize=1)
	@Column(name="showseat_id")
	private Integer showseatId;
    
	@Column(name="showseat_show_id")
	private Integer showseatShowId;
	
	@ManyToOne
	@JoinColumn(name="showseat_show_id", nullable = false,insertable=false, updatable=false)
	private ShowEntity show;

	@Column(name="showseat_row")
	private String showseatRow;

	@Column(name="showseat_seatno")
	private Integer showseatSeatno;
    
	@Enumerated(EnumType.ORDINAL)
	@Column(name="showseat_status")
	private Status showseatStatus;
	
	@Column(name="showseat_ticketcost")
	private Integer showseatTicketcost;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="showseat_createdon")
	private Timestamp showseatCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="showseat_updatedon")
	private Timestamp showseatUpdatedon;
}