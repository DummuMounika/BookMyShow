package com.project.microservices.paymentservice.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.paymentservice.utils.JsonTimestampSerializer;

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
@Table(name = "payments")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payments_payment_id_seq")
	@SequenceGenerator(name ="payments_payment_id_seq", sequenceName="payments_payment_id_seq", allocationSize=1)
	@Column(name="payment_id")
	private Integer paymentId;

	@Column(name="payment_type")
	private Integer paymentType;

	@Column(name="payment_totalamount")
	private Double paymentTotalamount;

	@Column(name="payment_status")
	private Integer paymentStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="payment_createdon")
	private Timestamp paymentCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="payment_updatedon")
	private Timestamp paymentUpdatedon;
}
