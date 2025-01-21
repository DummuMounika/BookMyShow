package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

public interface MessageService {
	
	public CompletableFuture<String> sendMessage(String phoneNumber,String message);

}
