package com.thuurzz.interactors;

import com.thuurzz.entities.User;

import java.util.List;

public interface UserUseCase {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> listUsers();

}
