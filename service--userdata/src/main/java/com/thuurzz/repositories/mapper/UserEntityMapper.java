package com.thuurzz.repositories.mapper;

import com.thuurzz.datasources.relational.entities.UserEntity;
import com.thuurzz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    User mapping(UserEntity userEntity);

    UserEntity mapping(User user);

}
