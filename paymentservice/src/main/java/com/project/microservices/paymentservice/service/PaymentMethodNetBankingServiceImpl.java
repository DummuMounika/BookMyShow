package com.project.microservices.paymentservice.service;

import org.springframework.stereotype.Service;

import com.project.microservices.paymentservice.model.NetBanking;
import com.project.microservices.paymentservice.model.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentMethodNetBankingServiceImpl implements PaymentMethod {

	@Override
	public boolean acceptPayment(float amount,PaymentRequest paymentRequest) {
		NetBanking netBanking = paymentRequest.getNetBanking();
		if(netBanking != null) {
			netBanking.getBankName();
			netBanking.getBankUserName();
			netBanking.getBankPassword();
			log.info("Accepting payment using Net Banking");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
		NetBanking netBanking = paymentRequest.getNetBanking();
		return !(netBanking == null || netBanking.getBankName().isBlank() || netBanking.getBankUserName().isBlank() ||
				netBanking.getBankPassword().isBlank());

	}

}
