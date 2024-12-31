package com.example.theaterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TheaterModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterModuleApplication.class, args);
	}

}
