package com.project.microservices.bookingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.microservices.bookingservice.model.ShowDetails;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.constraints.NotNull;

@FeignClient(name = "search-service")
public interface SearchServiceProxy {
	
	@GetMapping("/api/showdetails")
	//@CircuitBreaker(name = "fetchShowDetailsCircuitBreaker", fallbackMethod = "fetchShowDetailsFallback")
	@Retry(name = "fetchShowDetailsRetries", fallbackMethod = "fetchShowDetailsFallback")
	public ResponseEntity<ShowDetails> searchShowDetails(@RequestParam @NotNull Integer showId);
	
	default ResponseEntity<ShowDetails> fetchShowDetailsFallback(@RequestParam @NotNull Integer showId, Throwable e) {
		System.out.println("Retry is being called and fallback method got triggered: " + e.getMessage());
		//System.out.println("Circuit breaker fallback method triggered: " + e.getMessage());
		ShowDetails showDetails = new ShowDetails();
		showDetails.setShowId(showId);
	    return ResponseEntity.ok(showDetails);
	}

}
