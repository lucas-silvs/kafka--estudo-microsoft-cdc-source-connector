package com.thuurzz.transportlayer.mapper;

import com.thuurzz.entities.User;
import com.thuurzz.model.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
public interface UserDTOTransportMapper {

    UserDTOTransportMapper INSTANCE = Mappers.getMapper(UserDTOTransportMapper.class);

    User mapping(UserDTO userDTO);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    UserDTO mapping(User user);

}
