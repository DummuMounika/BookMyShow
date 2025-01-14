package com.project.microservices.bookingservice.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.microservices.bookingservice.exception.InvalidSeatIdException;
import com.project.microservices.bookingservice.exception.InvalidShowIdException;
import com.project.microservices.bookingservice.exception.SeatsUnavailableException;
import com.project.microservices.bookingservice.exception.ShowDetailsFetchException;
import com.project.microservices.bookingservice.model.BookingSummaryRequest;
import com.project.microservices.bookingservice.model.BookingSummaryResponse;
import com.project.microservices.bookingservice.model.SeatPricingDetails;
import com.project.microservices.bookingservice.model.ShowDetails;
import com.project.microservices.bookingservice.model.Status;
import com.project.microservices.bookingservice.proxy.SearchServiceProxy;
import com.project.microservices.bookingservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.bookingservice.showseats.repoistory.ShowSeatRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	private final SearchServiceProxy searchServiceProxy;
	private ShowSeatRepository showSeatRepository;

	
	@Autowired
	public BookingServiceImpl(SearchServiceProxy searchServiceProxy,ShowSeatRepository showSeatRepository) {
		super();
		this.searchServiceProxy = searchServiceProxy;
		this.showSeatRepository = showSeatRepository;
	}
 
	@Override
	public ShowDetails fetchShowDetailsFromSearchService(Integer showId) {
		log.info("Fetching show details for showId: {}", showId);
		ResponseEntity<ShowDetails> response = searchServiceProxy.searchShowDetails(showId);
		
		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			 log.info("Successfully fetched show details for showId: {}", showId);
	         return response.getBody();
		}else {
			 log.error("Failed to fetch show details for showId: {}", showId);
	         throw new ShowDetailsFetchException("Failed to fetch show details for showId: " + showId);   
	    }
	}

	@Override
	public BookingSummaryResponse generateBookingSummary(BookingSummaryRequest bookingSummaryRequest) {
		 
		if (bookingSummaryRequest == null || bookingSummaryRequest.getSeatsUniqueIds() == null ) {
		        throw new InvalidSeatIdException("Invalid booking summary request. Please provide valid seat Unique IDs.");
		    } 
		 
		 if (bookingSummaryRequest == null || bookingSummaryRequest.getShowId() == null ) {
		        throw new InvalidShowIdException("Invalid booking summary request. Please provide valid show ID.");
		    }
		 
		 Integer showId = bookingSummaryRequest.getShowId();
		 List<Integer> showSeatIds = bookingSummaryRequest.getSeatsUniqueIds();
		 
		 Long availableSeats = checkSeatAvailablity(showSeatIds,showId);
		 log.info("Total Available seats for given showId {}: {}", showId, availableSeats);
		
		 if(availableSeats == showSeatIds.size()) {
			 BookingSummaryResponse bookingSummaryResponse = new BookingSummaryResponse();
			 bookingSummaryResponse.setShowDetails(fetchShowDetailsFromSearchService(showId));
			 bookingSummaryResponse.setSeatPricingDetails(calculateSeatPricing(showSeatIds,showId));
			 updateSeatStatus(showSeatIds,2 , showId);		 
			 log.info("Booking summary generated successfully for showId: {}", showId);
			 return bookingSummaryResponse;
		 }else {
			 log.warn("Seats unavailable for showId: {}", showId);
			 throw new SeatsUnavailableException("Sorry! These seats are no longer available. Please try again with other seats.");
		 }
		  
	}

	@Override
	public String updateSeatStatus(List<Integer> seatUniqueId,Integer status,Integer showId) {
		log.info("Updating seat status for seat IDs: {} with status ID: {} and show ID: {}", seatUniqueId, status,showId);
		
		if (seatUniqueId == null || seatUniqueId.isEmpty()) {
			throw new InvalidSeatIdException("Please provide all valid seat IDs.");
	    }

	    if (status == null ) {
	        throw new SeatsUnavailableException("Please provide valid Status");
	    }
		showSeatRepository.updateSeatStatus(seatUniqueId,Status.fromIntValue(status),showId);	
		return "Seat statuses updated successfully.";
	}
	
	//(uniqueSEATIDs, showID,status) --check seats are available  --- isSeatAvailable -- use count
	
	
	private Long checkSeatAvailablity(List<Integer> seatUniqueIds, Integer showId) {
		
		if (seatUniqueIds == null || seatUniqueIds.isEmpty()) {
			throw new InvalidSeatIdException("SeatUniqueId cannot be null or empty.");
	    }
		
		if (showId == null) {
			throw new InvalidShowIdException("Invalid Show ID provided.");
	    }
		
	    // Ensure that only available seats are counted
	    List<ShowSeatsEntity> showSeatsEntityList = showSeatRepository.findByShowseatIdInAndShowseatShowId(seatUniqueIds,showId);
	    Long size = showSeatsEntityList.stream().filter(entity -> entity.getShowseatStatus() == Status.AVAILABLE).count();
	    log.info("Available seats count: {} for showId: {}", size, showId);
        return size;
	}
	


	private SeatPricingDetails calculateSeatPricing(List<Integer> seatUniqueIds,Integer showId) {
		if (seatUniqueIds == null || seatUniqueIds.isEmpty()) {
			throw new InvalidSeatIdException("SeatUniqueId cannot be null or empty.");
	    }
		
		SeatPricingDetails seatPricingDetails = new SeatPricingDetails();
		Integer selectedSeatCount = 0;
		Integer totalSeatCost = 0;
		float convenienceFee = 0;
		String seatLabel;
		
		List<ShowSeatsEntity> showSeatEntityList = showSeatRepository.findByShowseatIdInAndShowseatShowId(seatUniqueIds,showId);
		HashMap<String, Integer> selectedSeatMap = new HashMap<>();
		
		for(ShowSeatsEntity showSeatsEntity: showSeatEntityList) {
			seatLabel = showSeatsEntity.getShowseatRow()+showSeatsEntity.getShowseatSeatno();
			totalSeatCost += showSeatsEntity.getShowseatTicketcost();
			selectedSeatMap.put(seatLabel,showSeatsEntity.getShowseatId());
			selectedSeatCount++;
		}
		seatPricingDetails.setTotalSeats(selectedSeatCount);
		seatPricingDetails.setSeatsPrize(totalSeatCost);
		convenienceFee = (((float)totalSeatCost/100)*10);
		seatPricingDetails.setConvenienceFees(convenienceFee);
		seatPricingDetails.setSubTotalPrize(totalSeatCost+convenienceFee);
		seatPricingDetails.setSelectedSeats(selectedSeatMap);
		
		return seatPricingDetails;
		
	}
	
	}

