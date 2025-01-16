package com.project.microservices.bookingservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.bookingservice.utils.JsonTimestampSerializer;

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
public class Booking {

	private Integer bookingId;

	private Integer bookingUserId;

	private Integer bookingShowId;

	private Integer bookingTotalseats;

	private String bookingListofseats;

	private Integer bookingPaymentId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp bookingCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp bookingUpdatedon;
}
