package com.project.microservices.adminservice.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.microservices.adminservice.show.entity.ShowEntity;

@Repository
public interface ShowRespository extends JpaRepository<ShowEntity, Integer>{

}
