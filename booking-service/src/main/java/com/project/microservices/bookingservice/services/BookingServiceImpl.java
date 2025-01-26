package com.project.microservices.bookingservice.services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.microservices.bookingservice.entity.BookingEntity;
import com.project.microservices.bookingservice.exception.InvalidRequestBodyException;
import com.project.microservices.bookingservice.exception.InvalidSeatIdException;
import com.project.microservices.bookingservice.exception.InvalidShowIdException;
import com.project.microservices.bookingservice.exception.SeatsUnavailableException;
import com.project.microservices.bookingservice.exception.ShowDetailsFetchException;
import com.project.microservices.bookingservice.model.BookingDetails;
import com.project.microservices.bookingservice.model.BookingSummaryRequest;
import com.project.microservices.bookingservice.model.BookingSummaryResponse;
import com.project.microservices.bookingservice.model.SeatPricingDetails;
import com.project.microservices.bookingservice.model.ShowDetails;
import com.project.microservices.bookingservice.model.Status;
import com.project.microservices.bookingservice.proxy.SearchServiceProxy;
import com.project.microservices.bookingservice.repository.BookingRepository;
import com.project.microservices.bookingservice.showseats.entity.ShowSeatsEntity;
import com.project.microservices.bookingservice.showseats.repoistory.ShowSeatRepository;
import com.project.microservices.bookingservice.utils.Utility;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	private final SearchServiceProxy searchServiceProxy;
	private ShowSeatRepository showSeatRepository;
	private BookingRepository bookingRepository;

	
	@Autowired
	public BookingServiceImpl(SearchServiceProxy searchServiceProxy,ShowSeatRepository showSeatRepository,BookingRepository bookingRepository) {
		super();
		this.searchServiceProxy = searchServiceProxy;
		this.showSeatRepository = showSeatRepository;
		this.bookingRepository = bookingRepository;
	}
	 
	@Override
	public ShowDetails fetchShowDetailsFromSearchService(Integer showId) {
	    log.info("Fetching show details for showId: {}", showId);
	    try {
	        ResponseEntity<ShowDetails> response = searchServiceProxy.searchShowDetails(showId);
	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            log.info("Successfully fetched show details for showId: {}", showId);
	            return response.getBody();
	        } else {
	            log.error("Failed to fetch show details for showId: {}", showId);
	            throw new ShowDetailsFetchException("Failed to fetch show details for showId: " + showId);
	        }
		} catch (FeignException.ServiceUnavailable ex) {
			 log.error("Exception occurred while fetching show details for showId: {}", showId, ex);
		     throw new RuntimeException("Service unavailable", ex);
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
		 
		 Long countSeats = countSeatStatus(showSeatIds,showId, bookingSummaryRequest.getCurrentSeatStatus());
		 log.info("Total seats for given showId {}: {}", showId, countSeats);
		 log.info("checking isUpdaterequired: {} ",bookingSummaryRequest.getIsUpdateRequired() );
		
		 if(countSeats == showSeatIds.size()) {
			 BookingSummaryResponse bookingSummaryResponse = new BookingSummaryResponse();
			 bookingSummaryResponse.setShowDetails(fetchShowDetailsFromSearchService(showId));
			 bookingSummaryResponse.setSeatPricingDetails(calculateSeatPricing(showSeatIds,showId));
			 if(Boolean.TRUE.equals(bookingSummaryRequest.getIsUpdateRequired())) {
				 updateSeatStatus(showSeatIds,2 , showId);		
			 }
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
	
	private Long countSeatStatus(List<Integer> seatUniqueIds, Integer showId, Status status) {
		
		if (seatUniqueIds == null || seatUniqueIds.isEmpty()) {
			throw new InvalidSeatIdException("SeatUniqueId cannot be null or empty.");
	    }
		
		if (showId == null) {
			throw new InvalidShowIdException("Invalid Show ID provided.");
	    }
		
	    // Ensure that only available seats are counted
	    List<ShowSeatsEntity> showSeatsEntityList = showSeatRepository.findByShowseatIdInAndShowseatShowId(seatUniqueIds,showId);
	    Long size = showSeatsEntityList.stream().filter(entity -> entity.getShowseatStatus() == status).count();
	    log.info(status.getStringValue()+" seats count: {} for showId: {}", size, showId);
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

	@Override
	public Integer createBooking(BookingDetails bookingDetails) {
		 if (bookingDetails == null || 
			        bookingDetails.getSeatPricingDetails() == null ||
			        bookingDetails.getUserId() == null || 
			        bookingDetails.getShowId() == null || 
			        bookingDetails.getPaymentId() == null ) {
			        throw new InvalidRequestBodyException("Invalid booking request. Please provide all required fields.");
		 }
		
		BookingEntity bookingEntity = new BookingEntity();
		
		bookingEntity.setBookingUserId(bookingDetails.getUserId());
		bookingEntity.setBookingShowId(bookingDetails.getShowId());
		bookingEntity.setBookingTotalseats(bookingDetails.getSeatPricingDetails().getTotalSeats());
		bookingEntity.setBookingListofseats(bookingDetails.getSeatPricingDetails().getSelectedSeats().entrySet().stream().
				map(Map.Entry::getKey).collect(Collectors.joining(",")));
		bookingEntity.setBookingPaymentId(bookingDetails.getPaymentId());
		Timestamp timestamp = Utility.getCurrentTimestamp();
		bookingEntity.setBookingUpdatedon(timestamp);
		bookingEntity.setBookingCreatedon(timestamp);
		
		BookingEntity newEntity = bookingRepository.save(bookingEntity);
		
		Integer bookingId = newEntity.getBookingId();
		List<Integer> seatIds = bookingDetails.getSeatPricingDetails().getSelectedSeats().entrySet().stream().map(Map.Entry::getValue).toList();
	    updateSeatStatus(seatIds, bookingDetails.isPaymentStatus()?1:0, bookingDetails.getShowId());

		if (bookingId != null) {
	        log.info("Booking created successfully with ID: {}", bookingId);
	        return bookingId;
	    } else {
	        log.error("Failed to create booking for userId: {}", bookingDetails.getUserId());
	        throw new InvalidRequestBodyException("Booking creation failed");
	    }
		
	}

	
	
	
	}

