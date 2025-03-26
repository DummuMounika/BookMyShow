package com.project.microservices.adminservice.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.adminservice.movie.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

}
