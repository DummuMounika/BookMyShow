package com.project.microservices.adminservice.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.constraints.NotNull;

import com.project.microservices.adminservice.model.ShowSeatsResponse;

@FeignClient(name = "search-service")
public interface SearchServiceProxy {
	
	@GetMapping("/api/theater/seats")
	public ResponseEntity<ShowSeatsResponse> getShowSeatDetails(@RequestParam @NotNull Integer showId);
	

}
