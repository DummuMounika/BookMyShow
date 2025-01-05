package com.project.microservices.searchservice.movie.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MovieService {

	public List<String> findMoviesByNameAndCity(@Valid @NotNull String name, @NotNull String city);

}
