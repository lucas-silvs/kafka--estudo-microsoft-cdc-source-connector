package com.thuurzz.transportlayer.mapper;

import com.thuurzz.entities.User;
import com.thuurzz.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOTransportMapper {

    UserDTOTransportMapper INSTANCE = Mappers.getMapper(UserDTOTransportMapper.class);

    User mapping(UserDTO userDTO);

}
