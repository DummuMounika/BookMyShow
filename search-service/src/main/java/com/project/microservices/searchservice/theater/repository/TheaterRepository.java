package com.project.microservices.searchservice.theater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.searchservice.theater.entity.TheaterEntity;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
	
	List<TheaterEntity> findByCity(String city);

}
