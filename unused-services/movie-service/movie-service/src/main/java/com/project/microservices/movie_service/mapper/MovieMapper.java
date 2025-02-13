package com.project.microservices.movie_service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.microservices.movie_service.model.Movie;
import com.project.microservices.movie_service.modelentity.MovieEntity;

@Mapper
public interface MovieMapper {
	
	MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
	
	MovieEntity toEntity(Movie movie);
	
	Movie toModel(MovieEntity movieEntity);
	
	List<MovieEntity> toEntityList(List<Movie> movieList);
	
	List<Movie> toModelList(List<MovieEntity> entityList);

}
