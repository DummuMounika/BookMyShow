package com.project.microservices.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	
	private Integer notificationType;
	private NotificationDetails notificationDetails;
}
