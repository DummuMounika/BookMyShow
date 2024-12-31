package com.example.theaterservice.service;

import java.util.List;

import com.example.theaterservice.model.Theater;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface TheaterService {
	
	public List<Theater> getAllTheater();
	public Theater createTheater(Theater theater);
	public String deleteTheater(int theaterId);
	public Theater updateTheater(Theater theater, int theaterId);
	public Theater getSingleTheater(int theaterId);
	public Theater getTheaterDetailByName(String theaterName);

}
