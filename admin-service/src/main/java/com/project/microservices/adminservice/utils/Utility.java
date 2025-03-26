package com.project.microservices.adminservice.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utility {
	
	public static String convertTimeStampToHours(Timestamp timestamp) {
		LocalDateTime startTimeLocalDate = timestamp.toLocalDateTime();
		return String.format("%02d:%02d", startTimeLocalDate.getHour(), startTimeLocalDate.getMinute());
	}

}
