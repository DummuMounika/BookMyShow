package com.project.microservices.notificationservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest){
		return ResponseEntity.ok(notificationService.sendNotification(notificationRequest));
		
	}
}
