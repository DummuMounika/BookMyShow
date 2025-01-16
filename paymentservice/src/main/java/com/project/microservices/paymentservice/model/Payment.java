package com.project.microservices.paymentservice.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.paymentservice.utils.JsonTimestampSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	private Integer paymentId;

	private Integer paymentType;

	private Double paymentTotalamount;

	private Integer paymentStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp paymentCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp paymentUpdatedon;
}
