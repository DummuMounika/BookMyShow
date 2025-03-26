package com.project.microservices.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.microservices.adminservice.exception.InvalidIdException;
import com.project.microservices.adminservice.exception.ShowSeatsDetailsFetchException;
import com.project.microservices.adminservice.exception.ShowServiceUnavailableException;
import com.project.microservices.adminservice.model.ShowReqBody;
import com.project.microservices.adminservice.model.ShowSeatsResponse;
import com.project.microservices.adminservice.model.Status;
import com.project.microservices.adminservice.movie.entity.MovieEntity;
import com.project.microservices.adminservice.movie.repository.MovieRepository;
import com.project.microservices.adminservice.proxy.SearchServiceProxy;
import com.project.microservices.adminservice.show.entity.ShowEntity;
import com.project.microservices.adminservice.show.repository.ShowRespository;
import com.project.microservices.adminservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.adminservice.showseats.repository.ShowSeatsRepository;
import com.project.microservices.adminservice.theater.entity.TheaterEntity;
import com.project.microservices.adminservice.theater.repository.TheaterRepository;
import com.project.microservices.adminservice.theaterseats.entity.TheaterseatsEntity;
import com.project.microservices.adminservice.theaterseats.repository.TheaterseatsRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
	
	private ShowSeatsRepository showSeatsRepository;
	private ShowRespository showRespository;
	private TheaterRepository theaterRepository;
	private TheaterseatsRepository theaterseatsRepository;
	private final SearchServiceProxy searchServiceProxy;
	
	
    @Autowired
	public AdminServiceImpl(SearchServiceProxy searchServiceProxy,ShowSeatsRepository showSeatsRepository, ShowRespository showRespository, TheaterRepository theaterRepository
			,TheaterseatsRepository theaterseatsRepository) {
		super();
		this.searchServiceProxy = searchServiceProxy;
		this.showSeatsRepository = showSeatsRepository;
		this.showRespository = showRespository;
		this.theaterRepository = theaterRepository;
		this.theaterseatsRepository = theaterseatsRepository;
	}
    
    @Override
	public ShowSeatsResponse fetchShowSeatDetailsFromSearchService(Integer showId) {
	    log.info("Fetching show details for showId: {}", showId);
	    try {
	        ResponseEntity<ShowSeatsResponse> response = searchServiceProxy.getShowSeatDetails(showId);
	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            log.info("Successfully fetched show seat details for showId: {}", showId);
	            return response.getBody();
	        } else {
	            log.error("Failed to fetch show details for showId: {}", showId);
	            throw new ShowSeatsDetailsFetchException("Failed to fetch show details for showId: " + showId);
	        }
		} catch (FeignException.ServiceUnavailable ex) {
			 log.error("Exception occurred while fetching show details for showId: {}", showId, ex);
			 throw new ShowServiceUnavailableException("Show search service is unavailable", ex);
		}
	}
    
    @Override
    public ShowEntity addShow(ShowReqBody showInfo) {
    	if (showInfo.getShowMovieId() == null || showInfo.getShowTheaterId() == null) {
    	    throw new InvalidIdException("Movie ID and Theater ID must be provided.");
    	}
    	log.info("Received request to add show: {}", showInfo);  	
		log.info("Creating show:");
		ShowEntity showEntity = new ShowEntity();
		showEntity.setShowMovieId(showInfo.getShowMovieId());
		showEntity.setShowTheaterId(showInfo.getShowTheaterId());
		showEntity.setShowStarttime(showInfo.getShowStarttime());
		showEntity.setShowEndtime(showInfo.getShowEndtime());
		showEntity.setShowDate(showInfo.getShowDate());
		showEntity.setShowStatus("0");
		ShowEntity newShow = showRespository.save(showEntity);
		log.info("Creating show: "+showEntity);
		return newShow; 	
    }   
	
    @Override
	public ShowSeatsResponse addShowSeatDetails(ShowReqBody showInfo) {
		
		ShowEntity showEntity = addShow(showInfo);
		Integer showId = showEntity.getShowId();
	    
	    // Fetching List of Theater Seats
	    List<TheaterseatsEntity> listOfTheaterSeats = theaterseatsRepository.findByTheaterseatTheaterId(showEntity.getShowTheaterId());
        
	    if (listOfTheaterSeats.isEmpty()) {
	        throw new InvalidIdException("No seats found for theater ID: " + showEntity.getShowTheaterId());
	    }
	    
	    List<ShowSeatsEntity> showSeatsList = new ArrayList<>();
	    
	    for (TheaterseatsEntity seat : listOfTheaterSeats) {
	    	String seatRow = seat.getTheaterseatRow(); //a
	    	Integer totalSeatsForRow = seat.getTheaterseatNoofseats(); //10
	    	
	    	for(int i = 1; i <= totalSeatsForRow; i++) {
	    		ShowSeatsEntity showSeatsEntity = new ShowSeatsEntity();
	    		showSeatsEntity.setSeatstatusShowId(showId);
	    		showSeatsEntity.setShowseatRow(seatRow);
	    		showSeatsEntity.setShowseatSeatno(i);
	    		showSeatsEntity.setShowseatStatus(Status.AVAILABLE);
	    	    showSeatsEntity.setShowseatTicketcost(10);
	    		log.info("Saving show seat: {}", showSeatsEntity);
	    		showSeatsList.add(showSeatsEntity);
	    	}
	    }
	    
	    List<ShowSeatsEntity> addedSeats = showSeatsRepository.saveAll(showSeatsList);
	       
	    
	    //Fetching Theater Entity
	    Optional<TheaterEntity> optionalTheaterEntity = theaterRepository.findById(showInfo.getShowTheaterId());

	    if (optionalTheaterEntity.isEmpty()) {
	        throw new InvalidIdException("Theater not found for ID: " + showInfo.getShowTheaterId());
	    }
	    
	    TheaterEntity theaterEntity = optionalTheaterEntity.get();
	    
	    Integer theaterTotalSeats = theaterEntity.getTheaterTotalseats();
	    
	    //Checking total seats of theaters are added on show seats
	    boolean isTrue = theaterTotalSeats.equals(addedSeats.size());
	    
	    if(isTrue) {
	    	log.info("Matched with Total seats entries:"+theaterTotalSeats); 
	     }else {
	    	 log.error("Total seats entries are mismatched:"+theaterTotalSeats + " " + addedSeats.size()); 
	     }
	    
	    return fetchShowSeatDetailsFromSearchService(showId);
	}

}
