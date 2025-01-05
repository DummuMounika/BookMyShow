package com.project.microservices.searchservice.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.searchservice.movie.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer>  {
	
	Optional<MovieEntity> findByTitle(String title);
}
