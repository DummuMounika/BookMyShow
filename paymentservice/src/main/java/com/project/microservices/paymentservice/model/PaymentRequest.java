package com.project.microservices.paymentservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
	
	private List<Integer> uniqueSeatIds;
	private Integer showId;
	private Integer userId;
	private Integer paymentType;
	private UPI upi;
	private Card card;
	private NetBanking netBanking;
}
