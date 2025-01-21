package com.project.microservices.notificationservice.service;

import com.project.microservices.notificationservice.model.NotificationRequest;

public interface NotificationService {
	
	public String  sendNotification(NotificationRequest notificationRequest);
	

}
