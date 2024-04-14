package com.thuurzz.datasources;

import com.thuurzz.entities.User;

import java.util.List;

public class UserDataSourceMSSQLImpl implements UserDataSource{



    @Override
    public User createUser(User user) {
        return null;
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
