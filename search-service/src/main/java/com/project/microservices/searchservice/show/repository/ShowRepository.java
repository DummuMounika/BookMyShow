package com.project.microservices.searchservice.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.searchservice.movie.entity.MovieEntity;
import com.project.microservices.searchservice.show.entity.ShowEntity;
import com.project.microservices.searchservice.theater.entity.TheaterEntity;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	
	public List<ShowEntity> findByMovieAndTheater(MovieEntity movie, TheaterEntity theater);
}
