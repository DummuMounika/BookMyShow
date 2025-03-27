package com.project.microservices.searchservice.city.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.microservices.searchservice.city.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	
	@Query("SELECT DISTINCT cities.cityName FROM CityEntity cities")
	List<String> searchAllCities();

}
