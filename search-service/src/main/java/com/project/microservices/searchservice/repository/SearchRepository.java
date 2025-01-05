package com.project.microservices.searchservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.microservices.searchservice.model.SearchQueryResponse;
import com.project.microservices.searchservice.movie.entity.MovieEntity;

@Repository
public interface SearchRepository extends JpaRepository<MovieEntity, Integer> {

    // Custom JPQL query to fetch movie, theater, and show information in one go
    @Query("SELECT new com.project.microservices.search_service.model.SearchQueryResponse(" +
            "m.movieId, m.title, " +
            "t.theaterId, t.name, t.city, " +
            "s.showDate, s.startTime) " +
            "FROM MovieEntity m " +
            "JOIN ShowEntity s ON s.movie = m " +
            "JOIN TheaterEntity t ON s.theater = t " +
            "WHERE m.title = :movieName AND t.city = :city " +
            "ORDER BY s.showDate, s.startTime")
    List<SearchQueryResponse> searchMoviesAndTheaters(String movieName, String city);
}