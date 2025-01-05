package com.project.microservices.searchservice.service;

import com.project.microservices.searchservice.model.SearchResponse;

public interface SearchService {

	public SearchResponse findMovieByNameAndCity(String name, String city);

}
