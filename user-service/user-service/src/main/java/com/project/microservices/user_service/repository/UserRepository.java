package com.project.microservices.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservices.user_service.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(String email);

}
