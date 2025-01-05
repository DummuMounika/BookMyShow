package com.project.microservices.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.microservices.userservice.model.User;
import com.project.microservices.userservice.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserEntity toEntity(User user);
	
	User toModel(UserEntity userEntity);

}
