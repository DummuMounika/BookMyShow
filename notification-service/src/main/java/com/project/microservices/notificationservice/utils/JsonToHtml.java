package com.project.microservices.notificationservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToHtml {
	
	public static String jsonToHtml(String json) {
		try {
	        ObjectMapper mapper = new ObjectMapper();
	        Object jsonObject = mapper.readValue(json, Object.class);
	        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

	        // Convert to HTML-friendly format
	        return prettyJson
	            .replace("&", "&amp;")
	            .replace("<", "&lt;")
	            .replace(">", "&gt;")
	            .replace("\n", "<br>")
	            .replace(" ", "&nbsp;");
	    } catch (JsonProcessingException e) {
	        return "Invalid JSON format";
	    }
	}
}
