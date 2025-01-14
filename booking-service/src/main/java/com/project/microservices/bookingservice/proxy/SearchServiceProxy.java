package com.project.microservices.bookingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.microservices.bookingservice.model.ShowDetails;

import jakarta.validation.constraints.NotNull;

@FeignClient(name="search-service")
public interface SearchServiceProxy {
	
	@GetMapping("/api/showdetails")
	public ResponseEntity<ShowDetails> searchShowDetails(@RequestParam @NotNull Integer showId);
	
}
