package com.project.microservices.paymentservice.service;

import com.project.microservices.paymentservice.model.PaymentRequest;

public interface PaymentMethod {
	
	public boolean acceptPayment(float amount,PaymentRequest paymentRequest);
	
	public  boolean validatePaymentRequest(PaymentRequest paymentRequest);

}
