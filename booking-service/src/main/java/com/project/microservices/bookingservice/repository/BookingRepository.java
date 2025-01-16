package com.project.microservices.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.microservices.bookingservice.entity.BookingEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer>  {
//	
//	   @Modifying
//	   @Transactional
//	   @Query("INSERT INTO BookingEntity (bookingUserId, bookingShowId, bookingTotalseats, "
//	   		+ "bookingListofseats, bookingPaymentId) VALUES "
//	   		+ "(:userId,:showId,:totalSeats,:listOfSeats,:paymentId) RETURNING bookingId")
//	   Integer insertBookingAndReturnId(@Param("userId") Integer userId,@Param("showId") Integer showId,
//			   @Param("totalSeats") Integer totalSeats,
//			   @Param("listOfSeats") String listOfSeats,@Param("paymentId") Integer paymentId);
//	   
}
