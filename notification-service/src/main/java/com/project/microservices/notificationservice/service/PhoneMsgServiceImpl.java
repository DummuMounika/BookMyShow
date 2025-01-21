package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

public class PhoneMsgServiceImpl implements MessageService {

	@Override
	public CompletableFuture<String> sendMessage(String phoneNumber, String message) {
		return null;
	}


}
