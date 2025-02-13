package com.example.theaterservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.theaterservice.exception.NotFoundException;
import com.example.theaterservice.exception.TheaterNotFoundException;
import com.example.theaterservice.model.Theater;
import com.example.theaterservice.model.entity.TheaterEntity;
import com.example.theaterservice.respository.TheaterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TheaterServiceImplement implements TheaterService{
	
	private final TheaterRepository theaterRepository;
	String message = "Theater Id does not exist: ";
	
	@Autowired
	public TheaterServiceImplement(TheaterRepository theaterRepository) {
		super();
		this.theaterRepository = theaterRepository;
	}
	
	//convertMethods
	private Theater convertTheaterEntityToTheater(TheaterEntity theaterEntity) {
		return new Theater(theaterEntity.getTheaterId(),theaterEntity.getName(),theaterEntity.getAddress(),
				theaterEntity.getCity(),theaterEntity.getTotalSeats());
	}
	
	private TheaterEntity convertTheaterToTheaterEntity(Theater theater) {
		return new TheaterEntity(theater.getTheaterId(),theater.getName(),theater.getAddress(),theater.getCity(),
				theater.getTotalSeats());
	}
	
	private List<Theater> convertTheaterEntityListToTheaterList(List<TheaterEntity> theaterEntityList) {
		List<Theater> theaterList = new ArrayList<>();
		for(TheaterEntity theaterEntity: theaterEntityList) {
			Theater theater = convertTheaterEntityToTheater(theaterEntity);
			theaterList.add(theater);		
		}
		return theaterList;
	}

	@Override
	public Theater createTheater(Theater theater) {
		try {
			TheaterEntity theaterEntity = convertTheaterToTheaterEntity(theater);
			log.info("Creating theater: " +theaterEntity.toString());
			return convertTheaterEntityToTheater(theaterRepository.save(theaterEntity));
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Failed to create theater: "+e.getMessage());		
		}
	}


	
	

}
