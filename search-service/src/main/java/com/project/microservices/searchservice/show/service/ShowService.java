package com.project.microservices.searchservice.show.service;

import com.project.microservices.searchservice.show.model.ShowDetails;

public interface ShowService {
	
	public ShowDetails findShowDetails(Integer showId);

}
