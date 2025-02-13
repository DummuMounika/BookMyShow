package com.project.microservices.movie_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.movie_service.mapper.MovieMapper;
import com.project.microservices.movie_service.model.Movie;
import com.project.microservices.movie_service.modelentity.MovieEntity;
import com.project.microservices.movie_service.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImplement implements MovieService {
	
	private MovieRepository movieRepository;
	String message = "The movie id is not present";
	
	
    @Autowired
	public MovieServiceImplement(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	
	@Override
	public Movie createMovie(Movie movie) {
		try {
			MovieEntity movieEntity = MovieMapper.INSTANCE.toEntity(movie);
			log.info("Creating movie:" +movieEntity.toString());
		    MovieEntity newMovie = movieRepository.save(movieEntity);
		    return MovieMapper.INSTANCE.toModel(newMovie);
		}catch(Exception e) {
			log.info("Failed to create movie: "+e.getMessage());
			return null;
		}
		
	}



}
