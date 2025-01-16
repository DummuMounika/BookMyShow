package com.project.microservices.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	private Integer cardNumber;
	private String cardName;
	private Integer cardCvv;
	private Integer cardMonth;
	private Integer cardYear;

}
