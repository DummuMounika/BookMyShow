package com.project.microservices.notificationservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.microservices.notificationservice.exception.InvalidRequestBodyException;
import com.project.microservices.notificationservice.model.EmailDetails;
import com.project.microservices.notificationservice.model.MessageDetails;
import com.project.microservices.notificationservice.model.NotificationDetails;
import com.project.microservices.notificationservice.model.NotificationRequest;
import com.project.microservices.notificationservice.model.PushMsgDetails;
import com.project.microservices.notificationservice.utils.JsonToHtml;

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
		public String sendNotification(NotificationRequest notificationRequest) {
		    if (notificationRequest == null || notificationRequest.getNotificationType() == null || 
		            notificationRequest.getNotificationDetails() == null) {
		        log.error("Notification request is null");
		        throw new InvalidRequestBodyException("Notification request cannot be null");
		    }
		    log.info("Processing notification request: {}", notificationRequest);
		    
		    try {
		        if(notificationRequest.getNotificationType() == 1) {
		        	EmailDetails emailDetails = parseEmailData(notificationRequest.getNotificationDetails());
		        	return emailService.sendEmail(emailDetails.getRecipient(), "Your Payment Confirmation",
		        			emailDetails.getMsgBody());	            
		        } else if (notificationRequest.getNotificationType() == 2) {
		            return "Still not implemented yet!";
		        } else if (notificationRequest.getNotificationType() == 3) {
		        	return "Still not implemented yet!";
		        } else {
		        	return "Still not implemented yet!";
		        }
		    } catch (Exception e) {
		        log.error("Error occurred while sending notification", e);
		        return "Notification failed to send";
		    }
		}
		 
		 private EmailDetails parseEmailData(NotificationDetails notificationDetails) {
			  EmailDetails emailDetails = new EmailDetails();

			  try {
		            // Extract the notification details and prepare email context
		            String notificationDetailJson = new ObjectMapper().writeValueAsString(notificationDetails);
		            String prettyJsonHtml = JsonToHtml.jsonToHtml(notificationDetailJson);
		            log.info("Converted JSON to HTML: {}", prettyJsonHtml);
		            
		            NotificationDetails jsonData = new ObjectMapper().readValue(notificationDetailJson, NotificationDetails.class);
		            log.info("Only json: {}", jsonData);
		            
		            String selectedSeatsJson = notificationDetails.getSelectedSeats();
		            log.info("seats: {} ", selectedSeatsJson);
		            String selectedSeatsKeys = "";
		            
		            if (selectedSeatsJson != null && !selectedSeatsJson.isEmpty()) {
		                @SuppressWarnings("unchecked")
						Map<String, Integer> selectedSeatsMap = new ObjectMapper().readValue(
		                        selectedSeatsJson, Map.class);
		                log.info("Parsed selected seats map: {}", selectedSeatsMap);
		                selectedSeatsKeys = String.join(", ", selectedSeatsMap.keySet());
		                log.info("Parsed selected seats: {}", selectedSeatsKeys);
		            }
		            
		            // Prepare the Thymeleaf context
		            Context context = new Context();
//		            context.setVariable("subject",jsonData.getPaymentStatus());
		            context.setVariable("userEmail", jsonData.getUserEmail());
		            context.setVariable("userName", jsonData.getUserName());
		            context.setVariable("bookingId", jsonData.getBookingId());
		            context.setVariable("paymentId", jsonData.getPaymentId());
		            context.setVariable("paymentType", jsonData.getPaymentType());
		            context.setVariable("paymentStatus", jsonData.getPaymentStatus());
		            context.setVariable("movieName", jsonData.getMovieName());
		            context.setVariable("theaterName", jsonData.getTheaterName());
		            context.setVariable("showTime", jsonData.getShowTime());
		            context.setVariable("showDate", jsonData.getShowDate());
		            context.setVariable("selectedSeats", selectedSeatsKeys);
		            context.setVariable("totalSeats", jsonData.getTotalSeats());
		            context.setVariable("seatsPrize", jsonData.getSeatsPrize());
		            context.setVariable("convenienceFees", jsonData.getConvenienceFees());
		            context.setVariable("subTotalPrize", jsonData.getSubTotalPrize());
		            
		            // Render email body using Thymeleaf
		            String emailBody = templateEngine.process(
		                    "true".equals(jsonData.getPaymentStatus()) 
		                    ? "paymentSuccessTemplate" 
		                    : "paymentFailureTemplate", 
		                    context
		            );

		            emailDetails.setRecipient(jsonData.getUserEmail());
		            emailDetails.setMsgBody(emailBody);
		          

		        } catch (JsonProcessingException e) {
		            log.error("Error processing JSON for email content", e);
		            throw new RuntimeException("Error while preparing email content.");
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
