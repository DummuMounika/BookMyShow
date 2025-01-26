package com.project.microservices.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.microservices.paymentservice.model.User;

import jakarta.validation.constraints.NotNull;

@FeignClient(name="user-service")
public interface UserServiceProxy {
	
	@GetMapping("/api/userDetails")
	public ResponseEntity<User> userDetails(@RequestParam @NotNull Integer userId);

}
