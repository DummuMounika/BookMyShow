package com.project.microservices.paymentservice.service;

import org.springframework.stereotype.Service;

import com.project.microservices.paymentservice.model.PaymentRequest;
import com.project.microservices.paymentservice.model.UPI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentMethodUpiServiceImpl implements PaymentMethod{

	@Override
	public boolean acceptPayment(float amount, PaymentRequest paymentRequest) {
		UPI upi = paymentRequest.getUpi();
		if(upi != null) {
			upi.getUpiId();
			log.info("Accepting payment using Upi");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
		UPI upi = paymentRequest.getUpi();
		return !(upi == null || upi.getUpiId() == null || upi.getUpiId().isBlank());
	}

}
