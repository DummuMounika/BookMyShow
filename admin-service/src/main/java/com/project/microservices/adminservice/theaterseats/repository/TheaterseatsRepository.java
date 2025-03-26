package com.project.microservices.adminservice.theaterseats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.microservices.adminservice.theaterseats.entity.TheaterseatsEntity;

@Repository
public interface TheaterseatsRepository extends JpaRepository<TheaterseatsEntity, Integer> {
	
	List<TheaterseatsEntity> findByTheaterseatTheaterId(Integer theaterId);
	
}
