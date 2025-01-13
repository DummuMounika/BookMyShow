package com.project.microservices.searchservice.model;

import java.time.LocalDate;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterShows {
	
	private int theaterId;
	private String theaterName;
	private  HashMap<LocalDate,HashMap<String,Integer>> showTimes;
}
