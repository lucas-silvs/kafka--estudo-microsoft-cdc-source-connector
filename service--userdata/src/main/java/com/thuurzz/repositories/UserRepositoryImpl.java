package com.thuurzz.repositories;

import com.thuurzz.datasources.UserDataSource;
import com.thuurzz.entities.User;
import jakarta.inject.Inject;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    private UserDataSource userDataSource;

    @Override
    public User createUser(User user) {
        User newUser = userDataSource.createUser(user);
        return newUser;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User updateUser(String id, User user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public List<User> listUsers() {
        return List.of();
    }
}
