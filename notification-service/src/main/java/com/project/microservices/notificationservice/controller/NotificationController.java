package com.project.microservices.notificationservice.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.notificationservice.model.NotificationRequest;
import com.project.microservices.notificationservice.service.NotificationService;

@RestController
public class NotificationController {
	
	private NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@PostMapping("api/sendNotification")
	public String sendNotification(@RequestBody NotificationRequest notificationRequest) throws InterruptedException, ExecutionException{
		  CompletableFuture<String> status = notificationService.sendNotification(notificationRequest);
		  return status.get();
	}
}
