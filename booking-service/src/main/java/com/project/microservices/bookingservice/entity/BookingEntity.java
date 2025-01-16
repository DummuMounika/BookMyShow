package com.project.microservices.bookingservice.entity;

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
@Entity
@Table(name = "bookings")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bookings_booking_id_seq")
	@SequenceGenerator(name ="bookings_booking_id_seq", sequenceName="bookings_booking_id_seq", allocationSize=1)
	@Column(name="booking_id")
	private Integer bookingId;

	@Column(name="booking_user_id")
	private Integer bookingUserId;

	@Column(name="booking_show_id")
	private Integer bookingShowId;

	@Column(name="booking_totalseats")
	private Integer bookingTotalseats;

	@Column(name="booking_listofseats")
	private String bookingListofseats;

	@Column(name="booking_payment_id")
	private Integer bookingPaymentId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="booking_createdon")
	private Timestamp bookingCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="booking_updatedon")
	private Timestamp bookingUpdatedon;
}
