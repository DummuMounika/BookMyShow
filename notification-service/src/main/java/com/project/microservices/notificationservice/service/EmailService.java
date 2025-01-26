package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
	
	public CompletableFuture<String> sendEmail(String to, String subject, String body);
}
