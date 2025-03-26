package com.project.microservices.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatSubDetails {
	
	private Status status;
	private Integer showseatId;
	private Integer price;

}
