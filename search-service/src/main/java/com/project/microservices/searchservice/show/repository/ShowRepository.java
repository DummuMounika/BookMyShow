package com.project.microservices.searchservice.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.microservices.searchservice.movie.model.MovieResponse;
import com.project.microservices.searchservice.show.entity.ShowEntity;
import com.project.microservices.searchservice.show.model.ShowDetailsQueryResponse;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	
	@Query("SELECT new com.project.microservices.searchservice.show.model.ShowDetailsQueryResponse(" +
            "m.movieId, m.movieName, " +
            "t.theaterId, t.theaterName," +
            "s.showId,s.showStarttime,s.showDate) " +
            "FROM ShowEntity s " +
		    "JOIN s.theater t " +
		    "JOIN s.movie m " +
            "WHERE s.showId = :showId "+
            "ORDER BY s.showDate, s.showStarttime")
   ShowDetailsQueryResponse searchShowDetails(Integer showId);
   
	@Query(value = "SELECT DISTINCT m.movieName FROM ShowEntity s "
		     + "JOIN TheaterEntity t ON s.showTheaterId = t.theaterId "
		     + "JOIN MovieEntity m ON s.showMovieId = m.movieId "
		     + "WHERE t.theaterCity = :city")
		List<String> searchByCitiesToGetMovies(@Param("city") String city);
	
	@Query("SELECT new com.project.microservices.searchservice.movie.model.MovieResponse(" +
            "m.movieId, m.movieName) " +
        "FROM ShowEntity s " +
        "JOIN TheaterEntity t ON s.showTheaterId = t.theaterId " +
        "JOIN MovieEntity m ON s.showMovieId = m.movieId " +
        "WHERE t.theaterCityId = :cityId")
		List<MovieResponse> searchByCitiesToGetMovies1(@Param("cityId") Integer cityId);



}
