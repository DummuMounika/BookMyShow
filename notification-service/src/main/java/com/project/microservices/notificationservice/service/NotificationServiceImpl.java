package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.project.microservices.notificationservice.exception.InvalidRequestBodyException;
import com.project.microservices.notificationservice.model.EmailDetails;
import com.project.microservices.notificationservice.model.MessageDetails;
import com.project.microservices.notificationservice.model.NotificationDetails;
import com.project.microservices.notificationservice.model.NotificationRequest;
import com.project.microservices.notificationservice.model.PushMsgDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

		
		private EmailService emailService;
		private TemplateEngine templateEngine;
		
		@Autowired
		public NotificationServiceImpl(EmailService emailService, TemplateEngine templateEngine) {
			super();
			this.emailService = emailService;
			this.templateEngine = templateEngine;
		}
			
		@Override
		public CompletableFuture<String> sendNotification(NotificationRequest notificationRequest) {
		    if (notificationRequest == null || notificationRequest.getNotificationType() == null || 
		            notificationRequest.getNotificationDetails() == null) {
		        log.error("Notification request is null");
		        throw new InvalidRequestBodyException("Notification request cannot be null");
		    }
		    log.info("Processing notification request: {}", notificationRequest);
		    
		    try {
		        if(notificationRequest.getNotificationType() == 1) {
		        	EmailDetails emailDetails = parseEmailData(notificationRequest.getNotificationDetails());
		        	return emailService.sendEmail(emailDetails.getRecipient(),emailDetails.getSubject(),
		        			emailDetails.getMsgBody());	            
		        } else if (notificationRequest.getNotificationType() == 2) {
		            return CompletableFuture.completedFuture("Still not implemented yet!");
		        } else if (notificationRequest.getNotificationType() == 3) {
		        	return CompletableFuture.completedFuture("Still not implemented yet!");
		        } else {
		        	return CompletableFuture.completedFuture("Still not implemented yet!");
		        }
		    } catch (Exception e) {
		        log.error("Error occurred while sending notification", e);
		        return CompletableFuture.completedFuture("Notification failed to send");
		    }
		}
		 
		 private EmailDetails parseEmailData(NotificationDetails notificationDetails) {
			  EmailDetails emailDetails = new EmailDetails();

			  try {	     
        
		            // Prepare the Thymeleaf context
		            Context context = new Context();
		            context.setVariable("userEmail", notificationDetails.getUserEmail());
		            context.setVariable("userName", notificationDetails.getUserName());
		            context.setVariable("bookingId", notificationDetails.getBookingId());
		            context.setVariable("paymentId", notificationDetails.getPaymentId());
		            context.setVariable("paymentType", notificationDetails.getPaymentType());
		            context.setVariable("paymentStatus", notificationDetails.isPaymentStatus());
		            context.setVariable("movieName", notificationDetails.getMovieName());
		            context.setVariable("theaterName", notificationDetails.getTheaterName());
		            context.setVariable("showTime", notificationDetails.getShowTime());
		            context.setVariable("showDate", notificationDetails.getShowDate());
		            context.setVariable("selectedSeats",notificationDetails.getSelectedSeats());
		            context.setVariable("totalSeats", notificationDetails.getTotalSeats());
		            context.setVariable("seatsPrize", notificationDetails.getSeatsPrize());
		            context.setVariable("convenienceFees", notificationDetails.getConvenienceFees());
		            context.setVariable("subTotalPrize", notificationDetails.getSubTotalPrize());
		            
		            
		            // Render email body using Thymeleaf
		            String emailBody = templateEngine.process(notificationDetails.isPaymentStatus() 
		            		? "paymentSuccessTemplate"  : "paymentFailureTemplate", context);
                    
		            emailDetails.setRecipient(notificationDetails.getUserEmail());
		            emailDetails.setMsgBody(emailBody);
		            emailDetails.setSubject(notificationDetails.isPaymentStatus() ? "Ticket Booking Confirmation" : "Ticket Booking Failed");
		      
		        } catch (Exception e) {
		            log.error("Error sending email", e);
		            throw new RuntimeException("Error sending email.");
		        }	    
				return emailDetails;
				
			}
			

		 private MessageDetails parseWhatsappeMsgData() {
			return null;
		 }
		
		 private MessageDetails parsePhoneMsgData() {
			return null;
		 }
		 
		 private PushMsgDetails parsePushMessageData() {
				return null;
			 }

		
		
}
