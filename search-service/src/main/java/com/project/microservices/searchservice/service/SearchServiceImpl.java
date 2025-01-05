package com.project.microservices.searchservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.model.SearchResponse;
import com.project.microservices.searchservice.model.TheaterShows;
import com.project.microservices.searchservice.repository.SearchRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchServiceImpl  implements SearchService{
	
	private SearchRepository searchRepository;
	
	@Autowired
	public SearchServiceImpl(SearchRepository searchRepository) {
		super();
		this.searchRepository = searchRepository;
	}
	
	@Override
	public SearchResponse findMovieByNameAndCity(String name, String city) {
		List<SearchQueryResponse> searchQueryResponseList = searchRepository.searchMoviesAndTheaters(name, city);
		SearchResponse searchResponse = new SearchResponse();
		
		if(!searchQueryResponseList.isEmpty()) {
			searchResponse.setMovieId(searchQueryResponseList.get(0).getId());
			searchResponse.setMovieName(searchQueryResponseList.get(0).getMovieName());
			searchResponse.setCity(searchQueryResponseList.get(0).getTheaterCity());
			ArrayList<TheaterShows> theaterShowsList = new ArrayList<>();
//			HashMap<LocalDate,ArrayList<String>> showsMap = new HashMap<>();
			HashMap<Integer, HashMap<LocalDate,ArrayList<String>>> theaterShowTimings = new HashMap<>();
			
			for(SearchQueryResponse searchQueryResponse : searchQueryResponseList) {
				
				if(theaterShowTimings.containsKey(searchQueryResponse.getTheaterId())) {
					HashMap<LocalDate, ArrayList<String>> theaterShowTimingsValues = theaterShowTimings.get(searchQueryResponse.getTheaterId());
					if(theaterShowTimingsValues.containsKey(searchQueryResponse.getShowDate())) {
						ArrayList<String> showList = theaterShowTimingsValues.get(searchQueryResponse.getShowDate());
						showList.add(searchQueryResponse.getStartTime().toString());
						theaterShowTimings.get(searchQueryResponse.getTheaterId()).put(searchQueryResponse.getShowDate(), showList);
					}else {
						ArrayList<String> listOfShowTimes = new ArrayList<String>();
						listOfShowTimes.add(searchQueryResponse.getStartTime().toString());
						theaterShowTimingsValues.put(searchQueryResponse.getShowDate(),listOfShowTimes);
						theaterShowTimings.put(searchQueryResponse.getTheaterId(),theaterShowTimingsValues);
					}
				} else {
					HashMap<LocalDate,ArrayList<String>> dateAndShowTimes = new HashMap<>();
					ArrayList<String> listOfShowTimes = new ArrayList<>();
					listOfShowTimes.add(searchQueryResponse.getStartTime().toString());
					dateAndShowTimes.put(searchQueryResponse.getShowDate(), listOfShowTimes);
					
					theaterShowTimings.put(searchQueryResponse.getTheaterId(), dateAndShowTimes);
				}
				
			}	
			/*
			 * 
			 * {  
						02-01-2024: [12:30,2:30,4:30,9:30]
						03-01-2024: [05:43],
						04-01-2024: [06:30]
						
					}
			  { 1 04-01-2024 6:30
				1 : 
					{  
						02-01-2024: [12:30,2:30,4:30,9:30]
						03-01-2024: [05:43]
						
					},
				3: 
					{
						02-01-2024: [07:43,09:23]
					},
				2: 
					{	
						02-01-2024: [6:30]
						
					}
						
			  } 
			 */
					
			Set<Integer> theatersIds = new HashSet<>() ;
			
			for(SearchQueryResponse searchQueryResponse : searchQueryResponseList) {
		
				if(!theatersIds.contains(searchQueryResponse.getTheaterId())) {
					TheaterShows theaterShows = new TheaterShows();
					theaterShows.setTheaterId(searchQueryResponse.getTheaterId());
					theaterShows.setTheaterName(searchQueryResponse.getTheaterName());
					theaterShows.setShowTime(theaterShowTimings.get(searchQueryResponse.getTheaterId()));
					theaterShowsList.add(theaterShows);
					theatersIds.add(searchQueryResponse.getTheaterId());
				}
			}
			
			searchResponse.setTheaterShows(theaterShowsList);
		}
		
	
		return searchResponse;
		
	}



}
