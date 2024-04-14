package com.thuurzz.transportlayer;

import com.thuurzz.api.UsersApi;
import com.thuurzz.entities.User;
import com.thuurzz.interactors.UserUseCase;
import com.thuurzz.model.UserDTO;
import com.thuurzz.transportlayer.mapper.UserDTOTransportMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

public class UserControllerAPIUserImpl implements UsersApi {

    @Inject
    private UserUseCase userUseCase;

    private final UserDTOTransportMapper mapper = UserDTOTransportMapper.INSTANCE;

    @Override
    public Response createUserDTO(UserDTO userDTO) {
        User newUser = userUseCase.createUser(mapper.mapping(userDTO));
        UserDTO useResponse = mapper.mapping(newUser);
        return RestResponse.ResponseBuilder.create(Response.Status.CREATED, useResponse).build().toResponse();
    }

    @Override
    public Response deleteUserDTO(String id) {
        return null;
    }

    @Override
    public UserDTO listUserDTO(String id) {
        return null;
    }

    @Override
    public List<UserDTO> listUserDTOS() {
        return List.of();
    }

    @Override
    public Response updateUserDTO(String id, UserDTO userDTO) {
        return null;
    }
}
