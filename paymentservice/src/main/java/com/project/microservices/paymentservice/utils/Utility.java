package com.project.microservices.paymentservice.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utility {
	
	private Utility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String convertTimeStampToHours(Timestamp timestamp) {
        LocalDateTime startTimeLocalDate = timestamp.toLocalDateTime();
        return String.format("%02d:%02d", startTimeLocalDate.getHour(), startTimeLocalDate.getMinute());
    }

    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

}
