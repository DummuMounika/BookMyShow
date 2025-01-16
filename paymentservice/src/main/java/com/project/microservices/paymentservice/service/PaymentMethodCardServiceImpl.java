package com.project.microservices.paymentservice.service;

import org.springframework.stereotype.Service;

import com.project.microservices.paymentservice.model.Card;
import com.project.microservices.paymentservice.model.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentMethodCardServiceImpl implements PaymentMethod {

	@Override
	public boolean acceptPayment(float amount,PaymentRequest paymentRequest) {
		Card card = paymentRequest.getCard();
		if(card != null) {
			card.getCardNumber();
			card.getCardName();
			card.getCardCvv();
			card.getCardMonth();
			card.getCardYear();
			log.info("Accepting payment using Card");
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
		Card card = paymentRequest.getCard();
		return !(card == null || card.getCardNumber() == null || card.getCardName() == null || card.getCardName().isBlank() ||
				card.getCardCvv() == null || card.getCardMonth() == null || card.getCardYear() == null);
	}

}
