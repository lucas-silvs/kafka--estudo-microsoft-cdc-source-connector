package com.thuurzz.repositories;

import com.thuurzz.entities.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User getUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

    List<User> listUsers();
}
