package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

import com.project.microservices.notificationservice.model.NotificationRequest;

public interface NotificationService {
	
	public CompletableFuture<String>  sendNotification(NotificationRequest notificationRequest);
	

}
