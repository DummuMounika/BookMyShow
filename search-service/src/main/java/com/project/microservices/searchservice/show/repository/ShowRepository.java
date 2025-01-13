package com.project.microservices.searchservice.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
