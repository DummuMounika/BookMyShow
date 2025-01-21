package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

public interface PushMsgService {
	
	public CompletableFuture<String> sendPushMessage(String message);

}
