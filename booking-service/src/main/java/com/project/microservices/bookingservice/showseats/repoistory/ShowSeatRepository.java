package com.project.microservices.bookingservice.showseats.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.microservices.bookingservice.model.Status;
import com.project.microservices.bookingservice.showseats.entity.ShowSeatsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatsEntity, Integer> {
	

   List<ShowSeatsEntity> findByShowseatIdInAndShowseatShowId(List<Integer> seatIds, Integer showId);
   
   
   @Modifying
   @Transactional
   @Query("UPDATE ShowSeatsEntity s SET s.showseatStatus = :status"
   		+ " WHERE s.showseatId IN :seatUniqueIds AND s.showseatShowId = :showId ")
   void updateSeatStatus(@Param("seatUniqueIds") List<Integer> seatUniqueIds,@Param("status") Status status,@Param("showId") Integer showId);
   
   



  
}