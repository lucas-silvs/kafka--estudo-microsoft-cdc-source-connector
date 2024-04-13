package com.thuurzz.repositories;

import com.thuurzz.entities.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> listUsers();
}
