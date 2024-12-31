package com.project.microservices.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.microservices.user_service.model.User;
import com.project.microservices.user_service.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserEntity toEntity(User user);
	
	User toModel(UserEntity userEntity);

}
