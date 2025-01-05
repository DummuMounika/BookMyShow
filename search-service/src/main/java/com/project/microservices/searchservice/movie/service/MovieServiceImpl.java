package com.project.microservices.searchservice.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class MovieServiceImpl implements MovieService {

	@Override
	public List<String> findMoviesByNameAndCity(@Valid @NotNull String name, @NotNull String city) {
		return null;
	}

}
