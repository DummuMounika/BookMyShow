package com.project.microservices.paymentservice.service;

import org.springframework.stereotype.Component;

import com.project.microservices.paymentservice.exception.InvalidPaymentTypeException;


@Component
public class PaymentMethodFactory {
	
	public PaymentMethod getPaymentMethod(Integer paymentType) {
		if(paymentType == null) {
			return null;
		}
		
		switch(paymentType) {
			case 1:
				return new PaymentMethodUpiServiceImpl();
			case 2:
				return new PaymentMethodCardServiceImpl();
			case 3:
				return new PaymentMethodNetBankingServiceImpl();
			default:
				throw new InvalidPaymentTypeException("Unsupported payment type");
	    }
	}

}
