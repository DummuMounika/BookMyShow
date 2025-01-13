package com.project.microservices.searchservice.showseats.model;

import java.util.HashMap;

import com.project.microservices.searchservice.show.model.ShowDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatsResponse {
	
	private ShowDetails showDetails;
	private HashMap<String,HashMap<Integer,ShowSeatSubDetails>> seatDetails; 
	//String-(key): row , value: ((key)int-seatno,(value)Status-availablity)
}
	