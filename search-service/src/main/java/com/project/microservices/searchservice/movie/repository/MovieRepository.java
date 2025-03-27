package com.project.microservices.searchservice.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.model.SearchQueryResponse1;
import com.project.microservices.searchservice.movie.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer>  {
		
	//Custom JPQL query to fetch movie, theater, and show information in one go
    @Query("SELECT new com.project.microservices.searchservice.model.SearchQueryResponse(" +
            "m.movieId, m.movieName, " +
            "t.theaterId, t.theaterName, t.theaterCity, " +
            "s.showDate, s.showStarttime, s.showId) " +
            "FROM MovieEntity m " +
            "JOIN ShowEntity s ON s.movie = m " +
            "JOIN TheaterEntity t ON s.theater = t " +
            "WHERE m.movieName = :movieName AND t.theaterCity = :theaterCity " +
            "ORDER BY s.showDate, s.showStarttime")
   List<SearchQueryResponse> searchByMovieNameAndCity(String movieName, String theaterCity);
    
  //Custom JPQL query to fetch movie, theater, and show information in one go
    @Query("SELECT new com.project.microservices.searchservice.model.SearchQueryResponse1(" +
            "m.movieId, m.movieName, " +
            "t.theaterId, t.theaterName, t.theaterCityId, " +
            "s.showDate, s.showStarttime, s.showId) " +
            "FROM MovieEntity m " +
            "JOIN ShowEntity s ON s.movie = m " +
            "JOIN TheaterEntity t ON s.theater = t " +
            "WHERE m.movieId = :movieId AND t.theaterCityId = :cityId " +
            "ORDER BY s.showDate, s.showStarttime")
   List<SearchQueryResponse1> searchByMovieIdAndCityId(Integer movieId, Integer cityId);	
   
   @Query("SELECT movie.movieName FROM MovieEntity movie WHERE LOWER(movie.movieName) LIKE LOWER(CONCAT('%', :movieName, '%'))")
   List<String> findByMovieName(@Param("movieName") String movieName);
}
