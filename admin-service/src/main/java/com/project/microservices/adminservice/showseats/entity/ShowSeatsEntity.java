package com.project.microservices.adminservice.showseats.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.adminservice.model.Status;
import com.project.microservices.adminservice.show.entity.ShowEntity;
import com.project.microservices.adminservice.utils.JsonTimestampSerializer;

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
	private Integer seatstatusShowId;
	
	@ManyToOne
	@JoinColumn(name="showseat_show_id", nullable = false,insertable=false, updatable=false)
	private ShowEntity show;

	@Column(name="showseat_row")
	private String showseatRow;

	@Column(name="showseat_seatno")
	private Integer showseatSeatno;
    
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "showseat_status", nullable = false, insertable = false, updatable = true)
	private Status showseatStatus;
	
	@Column(name="showseat_ticketcost", insertable = false, updatable = true)
	private Integer showseatTicketcost;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="showseat_createdon", insertable = false, updatable = true)
	private Timestamp showseatCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="showseat_updatedon", insertable = false, updatable = true)
	private Timestamp showseatUpdatedon;
}