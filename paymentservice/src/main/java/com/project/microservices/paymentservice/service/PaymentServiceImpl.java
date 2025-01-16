package com.project.microservices.paymentservice.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.microservices.paymentservice.entity.PaymentEntity;
import com.project.microservices.paymentservice.exception.InvalidRequestBodyException;
import com.project.microservices.paymentservice.exception.PaymentProcessingException;
import com.project.microservices.paymentservice.model.BookingDetails;
import com.project.microservices.paymentservice.model.BookingSummaryRequest;
import com.project.microservices.paymentservice.model.BookingSummaryResponse;
import com.project.microservices.paymentservice.model.PaymentRequest;
import com.project.microservices.paymentservice.model.PaymentResponse;
import com.project.microservices.paymentservice.model.Status;
import com.project.microservices.paymentservice.proxy.BookingServiceProxy;
import com.project.microservices.paymentservice.repoistory.PaymentRepoistory;
import com.project.microservices.paymentservice.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final BookingServiceProxy bookingServiceProxy;
	private PaymentRepoistory paymentRepoistory;
	private PaymentMethodFactory paymentMethodFactory;

	private static final int PAYMENT_SUCCESS = 1;
	private static final int PAYMENT_FAILURE = 2;


	@Autowired
	public PaymentServiceImpl(BookingServiceProxy bookingServiceProxy, PaymentRepoistory paymentRepoistory,
			PaymentMethodFactory paymentMethodFactory) {
		super();
		this.bookingServiceProxy = bookingServiceProxy;
		this.paymentRepoistory = paymentRepoistory;
		this.paymentMethodFactory = paymentMethodFactory;
	}

	@Override
	public BookingSummaryResponse fetchBookingSummaryFromBookingService(BookingSummaryRequest bookingSummaryRequest) {
		log.info("Fetching summary details");
		ResponseEntity<BookingSummaryResponse> response = bookingServiceProxy.getbookingSummary(bookingSummaryRequest);

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			log.info("Successfully fetched booking summary details");
			return response.getBody();
		}else {
			log.error("Failed to fetch summary details from Booking Service");
			throw new InvalidRequestBodyException("Failed to fetch summary details from Booking Service");   
		}
	}

	@Override
	public Integer createBookingDetailsFromBookingService(BookingDetails bookingDetails) {
		log.info("Fetching booking row details");
		ResponseEntity<Integer> response = bookingServiceProxy.createBookingDetails(bookingDetails);

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			log.info("Successfully fetched booking row details");
			return response.getBody();
		}else {


			log.error("Failed to fetch booking row details");
			throw new InvalidRequestBodyException("Failed to fetch booking row details");   
		}
	}

	@Override
	public PaymentResponse goThroughPaymentProcess(PaymentRequest paymentRequest) {
        if(paymentRequest != null) {
			PaymentMethod paymentMethod = paymentMethodFactory.getPaymentMethod(paymentRequest.getPaymentType());
	
			if(paymentRequest.getUniqueSeatIds() == null || paymentRequest.getPaymentType() == null || 
					paymentRequest.getUserId() == null || paymentRequest.getShowId() == null || 
					!paymentMethod.validatePaymentRequest(paymentRequest)) {
				throw new InvalidRequestBodyException("Invalid Request body is passed");
			}
	 
			BookingSummaryRequest bookingSummaryRequest = new BookingSummaryRequest();
			bookingSummaryRequest.setShowId(paymentRequest.getShowId());
			bookingSummaryRequest.setSeatsUniqueIds(paymentRequest.getUniqueSeatIds());
			bookingSummaryRequest.setCurrentSeatStatus(Status.PENDING);
			bookingSummaryRequest.setIsUpdateRequired(false);
	
			// Fetch booking summary
			BookingSummaryResponse bookingSummary = fetchBookingSummaryFromBookingService(bookingSummaryRequest);
			if (bookingSummary == null || bookingSummary.getSeatPricingDetails() == null
					||bookingSummary.getShowDetails() == null) {
				throw new InvalidRequestBodyException("Invalid booking summary or seat pricing details or show details.");
			}
	
	
			float totalAmount = bookingSummary.getSeatPricingDetails().getSubTotalPrize();
			boolean isPaymentSuccess = paymentMethod.acceptPayment(totalAmount, paymentRequest);
			Integer paymentId = createPayment(paymentRequest.getPaymentType(),(double)totalAmount ,
					isPaymentSuccess?PAYMENT_SUCCESS: PAYMENT_FAILURE);
	
			BookingDetails bookingDetails = new BookingDetails();
			bookingDetails.setSeatPricingDetails(bookingSummary.getSeatPricingDetails());
			bookingDetails.setUserId(paymentRequest.getUserId());
			bookingDetails.setShowId(paymentRequest.getShowId());
	        bookingDetails.setPaymentStatus(isPaymentSuccess);
	        bookingDetails.setPaymentId(paymentId);
	        Integer bookingId = createBookingDetailsFromBookingService(bookingDetails);
	        
	        
			PaymentResponse paymentResponse = new PaymentResponse();
			paymentResponse.setPaymentType(paymentRequest.getPaymentType()); 
			paymentResponse.setBookingId(bookingId);
			paymentResponse.setPaymentId(paymentId);
			paymentResponse.setShowDetails(bookingSummary.getShowDetails());
			paymentResponse.setSeatPricingDetails(bookingSummary.getSeatPricingDetails());	
			paymentResponse.setPaymentStatus(isPaymentSuccess);
			return paymentResponse;
        }else {
        	throw new InvalidRequestBodyException("Request body is null");
        }
	}


	private Integer createPayment(Integer paymentType, Double paymentTotalAmount, Integer paymentStatus) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setPaymentType(paymentType);
		paymentEntity.setPaymentTotalamount(paymentTotalAmount);
		paymentEntity.setPaymentStatus(paymentStatus);
		Timestamp timestamp= Utility.getCurrentTimestamp();
		paymentEntity.setPaymentCreatedon(timestamp);
		paymentEntity.setPaymentUpdatedon(timestamp);

		try {
			return paymentRepoistory.save(paymentEntity).getPaymentId();
		} catch (Exception e) {
			log.error("Error while saving payment entity: {}", e.getMessage());
			throw new PaymentProcessingException("Error while creating payment");
		}
	}

}
