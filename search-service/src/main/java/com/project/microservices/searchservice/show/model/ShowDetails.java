package com.project.microservices.searchservice.show.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ShowDetails {
	
	private HashMap<LocalDate, ArrayList<ShowTime>> shows;
	
	@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShowTime {
        private String startTime;
//        private String endTime;
//        private String status;
    }

}
