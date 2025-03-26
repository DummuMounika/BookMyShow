package com.project.microservices.adminservice.theater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.microservices.adminservice.theater.entity.TheaterEntity;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
	
	List<TheaterEntity> findByTheaterCity(String theaterCity);
	
	@Query("SELECT DISTINCT theater.theaterCity FROM TheaterEntity theater")
	List<String> searchAllCities();

}
