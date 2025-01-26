package com.project.microservices.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.microservices.paymentservice.model.NotificationRequest;

@FeignClient(name="notification-service")
public interface NotificationServiceProxy {
	
	@PostMapping("api/sendNotification")
	public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest);

}
