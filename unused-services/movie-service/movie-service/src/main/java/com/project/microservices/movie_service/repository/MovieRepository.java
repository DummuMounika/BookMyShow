package com.project.microservices.movie_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.movie_service.modelentity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer>  {
	
	Optional<MovieEntity> findByTitle(String title);
}
