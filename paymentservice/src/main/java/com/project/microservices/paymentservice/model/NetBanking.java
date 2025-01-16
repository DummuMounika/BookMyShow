package com.project.microservices.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetBanking {
	
	private String bankName;
	private String bankUserName;
	private String bankPassword;

}
