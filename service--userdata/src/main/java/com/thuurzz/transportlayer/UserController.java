package com.thuurzz.transportlayer;

import com.thuurzz.transportlayer.models.UserDTO;

import java.util.List;

public interface UserController {
    UserDTO createUser(UserDTO user);

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);

    List<UserDTO> listUsers();
}
