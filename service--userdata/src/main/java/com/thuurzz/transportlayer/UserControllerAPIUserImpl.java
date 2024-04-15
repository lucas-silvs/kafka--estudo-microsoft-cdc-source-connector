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
        UserDTO userResponse = mapper.mapping(newUser);
        return RestResponse.ResponseBuilder.create(Response.Status.CREATED, userResponse).build().toResponse();
    }

    @Override
    public Response deleteUserDTO(String id) {
        userUseCase.deleteUser(id);
        return RestResponse.ResponseBuilder.create(Response.Status.ACCEPTED).build().toResponse();
    }

    @Override
    public UserDTO listUserDTO(String id) {
        User user = userUseCase.getUserById(id);
        UserDTO userResponse = mapper.mapping(user);
        return userResponse;
    }

    @Override
    public List<UserDTO> listUserDTOS() {
        List<User> listUsers = userUseCase.listUsers();
        List<UserDTO> listUserDTO = listUsers.stream().map(mapper::mapping).toList();
        return listUserDTO;
    }

    @Override
    public Response updateUserDTO(String id, UserDTO userDTO) {
        User userUpdated = userUseCase.updateUser(id, mapper.mapping(userDTO));
        return RestResponse.ResponseBuilder.create(Response.Status.CREATED, userUpdated).build().toResponse();
    }
}
