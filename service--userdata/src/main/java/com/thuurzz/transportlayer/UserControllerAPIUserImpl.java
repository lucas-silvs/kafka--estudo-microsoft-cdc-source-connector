package com.thuurzz.transportlayer;

import com.thuurzz.api.UsersApi;
import com.thuurzz.interactors.UserUseCase;
import com.thuurzz.model.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class UserControllerAPIUserImpl implements UsersApi {

    @Inject
    UserUseCase userUseCase;

    @Override
    public Response createUserDTO(UserDTO userDTO) {
        return null;
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
