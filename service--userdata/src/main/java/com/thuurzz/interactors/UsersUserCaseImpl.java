package com.thuurzz.interactors;

import com.thuurzz.entities.User;
import com.thuurzz.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsersUserCaseImpl implements UserUseCase {

    @Inject
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User newUser = userRepository.createUser(user);
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
        List<User> listUsers = userRepository.listUsers();
        return listUsers;
    }
}
