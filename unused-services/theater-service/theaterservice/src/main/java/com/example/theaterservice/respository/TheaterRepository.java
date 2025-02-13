package com.example.theaterservice.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.theaterservice.model.entity.TheaterEntity;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
	
	Optional<TheaterEntity> findByName(String name);

}
