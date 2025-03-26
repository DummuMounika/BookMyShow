package com.project.microservices.adminservice.showseats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.microservices.adminservice.showseats.entity.ShowSeatsEntity;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeatsEntity, Integer>{

}
