package com.project.microservices.notificationservice.service;

public interface EmailService {
	
		public String sendEmail(String to, String subject,String body);
}
