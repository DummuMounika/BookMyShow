package com.project.microservices.searchservice.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.searchservice.exception.InvalidShowIDException;
import com.project.microservices.searchservice.show.model.ShowDetails;
import com.project.microservices.searchservice.show.model.ShowDetailsQueryResponse;
import com.project.microservices.searchservice.show.repository.ShowRepository;
import com.project.microservices.searchservice.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShowServiceImpl implements ShowService {
	
	private ShowRepository showRepository;
	
	@Autowired
	public ShowServiceImpl(ShowRepository showRepository) {
		super();
		this.showRepository = showRepository;
	}

	@Override
	public ShowDetails findShowDetails(Integer showId) {
		ShowDetails showDetails = new ShowDetails();
		ShowDetailsQueryResponse showDetailsQueryResponse = showRepository.searchShowDetails(showId);
		if(showDetailsQueryResponse == null) {
			 log.warn("No seat details found for showId: {}", showId);
			 throw new InvalidShowIDException("No show details found for the given id: " + showId);	
		}else {
			showDetails.setMovieId(showDetailsQueryResponse.getMovieId());
			showDetails.setMovieName(showDetailsQueryResponse.getMovieName());
			showDetails.setTheaterId(showDetailsQueryResponse.getTheaterId());
			showDetails.setTheaterName(showDetailsQueryResponse.getTheaterName());
			showDetails.setShowId(showDetailsQueryResponse.getShowId());
			showDetails.setShowDate(showDetailsQueryResponse.getShowDate());
			String showTime = Utility.convertTimeStampToHours(showDetailsQueryResponse.getShowTime());
			showDetails.setShowTime(showTime);
		}
		return showDetails;
	}

}
