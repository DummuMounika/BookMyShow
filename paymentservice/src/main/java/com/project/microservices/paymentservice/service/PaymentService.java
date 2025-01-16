package com.project.microservices.paymentservice.service;

import com.project.microservices.paymentservice.model.BookingDetails;
import com.project.microservices.paymentservice.model.BookingSummaryRequest;
import com.project.microservices.paymentservice.model.BookingSummaryResponse;
import com.project.microservices.paymentservice.model.PaymentRequest;
import com.project.microservices.paymentservice.model.PaymentResponse;

public interface PaymentService {
	
	public BookingSummaryResponse fetchBookingSummaryFromBookingService(BookingSummaryRequest bookingSummaryRequest);
	
	public Integer createBookingDetailsFromBookingService(BookingDetails bookingDetails);

	public PaymentResponse goThroughPaymentProcess(PaymentRequest paymentRequest);

}
