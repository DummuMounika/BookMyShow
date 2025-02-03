package com.project.microservices.searchservice.showseats.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.searchservice.exception.InvalidShowIDException;
import com.project.microservices.searchservice.model.Status;
import com.project.microservices.searchservice.show.model.ShowDetails;
import com.project.microservices.searchservice.showseats.model.ShowSeatSubDetails;
import com.project.microservices.searchservice.showseats.model.ShowSeatsQueryResponse;
import com.project.microservices.searchservice.showseats.model.ShowSeatsResponse;
import com.project.microservices.searchservice.showseats.repository.ShowSeatsRepository;
import com.project.microservices.searchservice.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShowSeatsServiceImpl implements ShowSeatsService {

	private ShowSeatsRepository showSeatRepository;

	@Autowired
	public ShowSeatsServiceImpl(ShowSeatsRepository showSeatRepository) {
		super();
		this.showSeatRepository = showSeatRepository;
	}

	@Override
	public ShowSeatsResponse findShowSeatDetails(int showId) {
	
		ShowSeatsResponse showSeatResponse = new ShowSeatsResponse();
		ShowDetails showDetails = new ShowDetails();
		try {
			List<ShowSeatsQueryResponse> seatStatusQueryResponseList = 
					showSeatRepository.findByShowSeatsFromShowId(showId);
			
			if (seatStatusQueryResponseList == null || seatStatusQueryResponseList.isEmpty()) {
			    log.warn("No seat details found for showId: {}", showId);
			    throw new InvalidShowIDException("No shows found for the given id: " + showId);
			}else {	
					showDetails.setMovieId(seatStatusQueryResponseList.get(0).getMovieId());
					showDetails.setMovieName(seatStatusQueryResponseList.get(0).getMovieName());
					showDetails.setTheaterId(seatStatusQueryResponseList.get(0).getTheaterId());
					showDetails.setTheaterName(seatStatusQueryResponseList.get(0).getTheaterName());
					showDetails.setShowId(seatStatusQueryResponseList.get(0).getShowId());
					showDetails.setShowTime(Utility.convertTimeStampToHours(seatStatusQueryResponseList.get(0).getShowStarttime()));
					showDetails.setShowDate(seatStatusQueryResponseList.get(0).getShowDate());
					showSeatResponse.setShowDetails(showDetails);
					HashMap<String,HashMap<Integer,ShowSeatSubDetails>> seatDetailsMap = new HashMap<>();

					for (ShowSeatsQueryResponse response: seatStatusQueryResponseList) {
						String rows = response.getShowseatRow();
						Integer seatNo = response.getShowseatSeatno();
						Status seatStatus = response.getShowseatStatus();
						HashMap<Integer,ShowSeatSubDetails> rowSeats = new HashMap<>(); //create a new Hashmap
						
						ShowSeatSubDetails showSeatSubDetails = new ShowSeatSubDetails();
						
						showSeatSubDetails.setShowseatId(response.getShowseatId());
						showSeatSubDetails.setStatus(seatStatus);
						showSeatSubDetails.setPrice(response.getShowseatSeatTicketCost());

						if(seatDetailsMap.containsKey(rows)) {   //Check if the row is already exists in the map
							rowSeats = seatDetailsMap.get(rows); //Add the seats to existing row entry
						} 
						rowSeats.put(seatNo, showSeatSubDetails);
						seatDetailsMap.put(rows,rowSeats);
					}
					showSeatResponse.setSeatDetails(seatDetailsMap);
				}
		} catch (InvalidShowIDException e) {
			log.warn("No seat details found for showId: {}", showId);
            throw e;			
		} catch (Exception e) {
			log.error("Exception occurred while fetching seat details for showId: {}. Error: {}", showId, e.getMessage(), e);
		}
		return showSeatResponse;
		


		
	}
}
