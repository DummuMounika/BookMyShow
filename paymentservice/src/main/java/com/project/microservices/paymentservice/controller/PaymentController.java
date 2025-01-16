package com.project.microservices.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.paymentservice.model.PaymentRequest;
import com.project.microservices.paymentservice.model.PaymentResponse;
import com.project.microservices.paymentservice.service.PaymentService;

@RestController
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@PostMapping("api/payment")
	public ResponseEntity<PaymentResponse> getPaymentProcess(@RequestBody PaymentRequest paymentRequest){
		return ResponseEntity.ok(paymentService.goThroughPaymentProcess(paymentRequest));
	}

}
