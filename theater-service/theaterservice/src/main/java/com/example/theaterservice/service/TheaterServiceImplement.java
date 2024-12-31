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
		return new Theater(theaterEntity.getTheaterId(),theaterEntity.getName(),theaterEntity.getLocation(),
				theaterEntity.getTotalSeats());
	}
	
	private TheaterEntity convertTheaterToTheaterEntity(Theater theater) {
		return new TheaterEntity(theater.getTheaterId(),theater.getName(),theater.getLocation(),
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
	public List<Theater> getAllTheater() {
		List<TheaterEntity> theaterEntityList = theaterRepository.findAll();
		if(theaterEntityList.isEmpty()) {
			throw new TheaterNotFoundException("No theaters found");
		}
		return convertTheaterEntityListToTheaterList(theaterEntityList);
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

	@Override
	public String deleteTheater(int theaterId) {
		Optional<TheaterEntity> removeTheaterEntity = theaterRepository.findById(theaterId);
		if(removeTheaterEntity.isPresent()) {
			theaterRepository.deleteById(theaterId);
			return "The theater " + theaterId + " deleted successfully" ;	
		}else {
			throw new TheaterNotFoundException(message + theaterId );
		}
	}

	@Override
	public Theater updateTheater(Theater theater, int theaterId) {
		Optional<TheaterEntity> existingTheaterEntity = theaterRepository.findById(theaterId);
		if(existingTheaterEntity.isPresent()) {
			TheaterEntity theaterEntity = existingTheaterEntity.get();
	        theaterEntity.setName(theater.getName());
	        theaterEntity.setLocation(theater.getLocation());
	        theaterEntity.setTotalSeats(theater.getTotalSeats());
	        return convertTheaterEntityToTheater(theaterRepository.save(theaterEntity));
	    } else {
	    	throw new TheaterNotFoundException(message + theaterId );
	    }
	}

	@Override
	public Theater getSingleTheater(int theaterId) {
		Optional<TheaterEntity> singleTheaterEntity = theaterRepository.findById(theaterId);
		if(singleTheaterEntity.isPresent()) {
			return convertTheaterEntityToTheater(singleTheaterEntity.get());
		}else {
			throw new TheaterNotFoundException(message + theaterId );
		}
	}
	
	@Override
	public Theater getTheaterDetailByName(String theaterName) {
		Optional<TheaterEntity> singleTheaterEntity = theaterRepository.findByName(theaterName);
		if(singleTheaterEntity.isPresent()) {
			return convertTheaterEntityToTheater(singleTheaterEntity.get());
		}else {
			throw new TheaterNotFoundException("Given Theater name " +theaterName+ " does not exist");
		}
	}

}
