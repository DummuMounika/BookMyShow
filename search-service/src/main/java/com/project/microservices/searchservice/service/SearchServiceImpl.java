package com.project.microservices.searchservice.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservices.searchservice.city.entity.CityEntity;
import com.project.microservices.searchservice.city.repository.CityRepository;
import com.project.microservices.searchservice.exception.TheaterNotFoundException;
import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.model.SearchQueryResponse1;
import com.project.microservices.searchservice.model.SearchResponse;
import com.project.microservices.searchservice.model.TheaterShows;
import com.project.microservices.searchservice.movie.service.MovieService;
import com.project.microservices.searchservice.theater.repository.TheaterRepository;
import com.project.microservices.searchservice.utils.Utility;

import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchServiceImpl  implements SearchService{
	
	private MovieService movieService;
	private TheaterRepository theaterRepository;
	private CityRepository cityRepository;
	
	
	@Autowired
	public SearchServiceImpl(MovieService movieService,TheaterRepository theaterRepository,CityRepository cityRepository) {
		super();
		this.movieService = movieService;
		this.theaterRepository = theaterRepository;
		this.cityRepository = cityRepository;
	}
	
	@Override
	public SearchResponse findTheatersByMovieNameAndTheaterCity(String movieName, String theaterCity) {
		    SearchResponse searchResponse = new SearchResponse();
			try {
				List<SearchQueryResponse> searchQueryResponseList = movieService.findTheatersByMovieNameAndTheaterCity(movieName, theaterCity);
				log.info("Number of search results found: {}", searchQueryResponseList.size());

				if(searchQueryResponseList.isEmpty() || searchQueryResponseList == null) {
					throw new TheaterNotFoundException("No theaters found for the given movie: " +movieName +"and city:"+theaterCity);
				}else {
						Integer movieId = searchQueryResponseList.get(0).getMovieId();
						String name = searchQueryResponseList.get(0).getMovieName();
						String city = searchQueryResponseList.get(0).getTheaterCity();

						searchResponse.setMovieId(movieId);
						searchResponse.setMovieName(name);
						searchResponse.setTheaterCity(city);

						ArrayList<TheaterShows> theaterShowsList = new ArrayList<>();
						HashMap<Integer, HashMap<LocalDate,HashMap<String,Integer>>> theaterShowTimings = new HashMap<>();

						for(SearchQueryResponse searchQueryResponse : searchQueryResponseList) {

							Integer theaterId = searchQueryResponse.getTheaterId();
							Integer showId = searchQueryResponse.getShowId();
							LocalDate showDate = searchQueryResponse.getShowDate();
							Timestamp showStartTime = searchQueryResponse.getShowStarttime();

							HashMap<String, Integer> listOfShowTimes = new HashMap<>();
							String startTime = Utility.convertTimeStampToHours(showStartTime);

							if(theaterShowTimings.containsKey(theaterId)) {

								HashMap<LocalDate,HashMap<String,Integer>> theaterShowTimingsValues =
										theaterShowTimings.get(theaterId);

								if(theaterShowTimingsValues.containsKey(showDate)) {
									listOfShowTimes = theaterShowTimingsValues.get(showDate);
									listOfShowTimes.put(startTime,showId);
									theaterShowTimings.get(theaterId).put(showDate, listOfShowTimes);
								}else {
									listOfShowTimes.put(startTime,showId);
									theaterShowTimingsValues.put(showDate,listOfShowTimes);
									theaterShowTimings.put(theaterId,theaterShowTimingsValues);
								}
							} else {
								HashMap<LocalDate,HashMap<String, Integer>> dateAndShowTimes = new HashMap<>();
								listOfShowTimes.put(startTime,showId);
								dateAndShowTimes.put(showDate, listOfShowTimes);
								theaterShowTimings.put(theaterId, dateAndShowTimes);
							}
						}

					Set<Integer> theatersIds = new HashSet<>() ;

					for(SearchQueryResponse searchQueryResponse : searchQueryResponseList) {

						if(!theatersIds.contains(searchQueryResponse.getTheaterId())) {
							TheaterShows theaterShows = new TheaterShows();
							theaterShows.setTheaterId(searchQueryResponse.getTheaterId());
							theaterShows.setTheaterName(searchQueryResponse.getTheaterName());
							theaterShows.setShowTimes(theaterShowTimings.get(searchQueryResponse.getTheaterId()));
							theaterShowsList.add(theaterShows);
							theatersIds.add(searchQueryResponse.getTheaterId());
						}
					}

					searchResponse.setTheaterShows(theaterShowsList);
				}
			} catch (TheaterNotFoundException e) {
				log.warn("No theater details found for movieName: {}  and city: {}", movieName,theaterCity);
	            throw e;
			} catch (Exception e) {
				log.error("Exception occurred while fetching theater details for movieName: {}  and city: {}. Error: {}", movieName ,theaterCity, e.getMessage(), e);
			}
			return searchResponse;
	}

	@Override
	public List<String> getAllCities() {
		List<String> cities = theaterRepository.searchAllCities();
		if(cities.isEmpty()) {
			throw new NotFoundException("No cities found from the entity");
		}
		return cities;
	}
	
	@Override
	public HashMap<Integer, String> getAllCities1() {
	    List<CityEntity> cityEntity = cityRepository.findAll();
	    if (cityEntity.isEmpty()) {
	        throw new NotFoundException("No cities found from the entity");
	    }

	    // Initialize the map before usage
	    HashMap<Integer, String> mapList = new HashMap<>();

	    // Populate the map with cityId as key and cityName as value
	    for (CityEntity entity : cityEntity) {
	        mapList.put(entity.getCityId(), entity.getCityName());
	    }

	    return mapList;
	}
	
	@Override
	public SearchResponse findTheatersByMovieIdAndTheaterCityId(Integer movieId, Integer theaterCityId) {
		SearchResponse searchResponse = new SearchResponse();
		try {
			List<SearchQueryResponse1> searchQueryResponseList = movieService.findTheatersByMovieIdAndTheaterId(movieId, theaterCityId);
			log.info("Number of search results found: {}", searchQueryResponseList.size());
				
			if(searchQueryResponseList.isEmpty() || searchQueryResponseList == null) {
				throw new TheaterNotFoundException("No theaters found for the given movie: " +movieId +"and city:"+theaterCityId);
			}else {
					Integer theaterMovieId = searchQueryResponseList.get(0).getMovieId();
					String name = searchQueryResponseList.get(0).getMovieName();
					//String city = searchQueryResponseList.get(0).getTheaterCity();
					//Integer cityId = searchQueryResponseList.get(0).getCityId();
					
					searchResponse.setMovieId(theaterMovieId);
					searchResponse.setMovieName(name);
					Optional<CityEntity> cityEntity = cityRepository.findById(theaterCityId);
					if(cityEntity.isPresent()) {
						searchResponse.setTheaterCity(cityEntity.get().getCityName());
					}
					
					ArrayList<TheaterShows> theaterShowsList = new ArrayList<>();
					HashMap<Integer, HashMap<LocalDate,HashMap<String,Integer>>> theaterShowTimings = new HashMap<>();
					
					for(SearchQueryResponse1 searchQueryResponse : searchQueryResponseList) {
						
						Integer theaterId = searchQueryResponse.getTheaterId();
						Integer showId = searchQueryResponse.getShowId();
						LocalDate showDate = searchQueryResponse.getShowDate();
						Timestamp showStartTime = searchQueryResponse.getShowStarttime();

						HashMap<String, Integer> listOfShowTimes = new HashMap<>();
						String startTime = Utility.convertTimeStampToHours(showStartTime);
						
						if(theaterShowTimings.containsKey(theaterId)) {
							
							HashMap<LocalDate,HashMap<String,Integer>> theaterShowTimingsValues = 
									theaterShowTimings.get(theaterId);
							
							if(theaterShowTimingsValues.containsKey(showDate)) {
								listOfShowTimes = theaterShowTimingsValues.get(showDate);
								listOfShowTimes.put(startTime,showId);
								theaterShowTimings.get(theaterId).put(showDate, listOfShowTimes);
							}else {
								listOfShowTimes.put(startTime,showId);
								theaterShowTimingsValues.put(showDate,listOfShowTimes);
								theaterShowTimings.put(theaterId,theaterShowTimingsValues);
							}
						} else {
							HashMap<LocalDate,HashMap<String, Integer>> dateAndShowTimes = new HashMap<>();
							listOfShowTimes.put(startTime,showId);
							dateAndShowTimes.put(showDate, listOfShowTimes);	
							theaterShowTimings.put(theaterId, dateAndShowTimes);
						}	
					}	
					
				Set<Integer> theatersIds = new HashSet<>() ;
				
				for(SearchQueryResponse1 searchQueryResponse : searchQueryResponseList) {
			
					if(!theatersIds.contains(searchQueryResponse.getTheaterId())) {
						TheaterShows theaterShows = new TheaterShows();
						theaterShows.setTheaterId(searchQueryResponse.getTheaterId());
						theaterShows.setTheaterName(searchQueryResponse.getTheaterName());
						theaterShows.setShowTimes(theaterShowTimings.get(searchQueryResponse.getTheaterId()));
						theaterShowsList.add(theaterShows);
						theatersIds.add(searchQueryResponse.getTheaterId());
					}
				}
				
				searchResponse.setTheaterShows(theaterShowsList);
			}
		} catch (TheaterNotFoundException e) {
			log.warn("No theater details found for movieName: {}  and city: {}", movieId,theaterCityId);
            throw e;			
		} catch (Exception e) {
			log.error("Exception occurred while fetching theater details for movieName: {}  and city: {}. Error: {}", movieId ,theaterCityId, e.getMessage(), e);
		}
		return searchResponse;
	}




}
