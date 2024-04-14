package com.thuurzz.interactors;

import com.thuurzz.entities.User;

import java.util.List;

public interface UserUseCase {
    User createUser(User user);

    User getUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

    List<User> listUsers();

}
