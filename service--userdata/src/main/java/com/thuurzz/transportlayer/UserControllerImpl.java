package com.thuurzz.transportlayer;

import com.thuurzz.interactors.UserUseCase;
import com.thuurzz.transportlayer.models.UserDTO;
import jakarta.inject.Inject;

import java.util.List;

public class UserControllerImpl implements UserController {

    @Inject
    UserUseCase userUseCase;

    @Override
    public UserDTO createUser(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<UserDTO> listUsers() {
        return List.of();
    }
}
