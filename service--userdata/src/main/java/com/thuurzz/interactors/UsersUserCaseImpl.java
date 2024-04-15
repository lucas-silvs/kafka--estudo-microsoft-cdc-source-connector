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
        return userRepository.getUserById(id);
    }

    @Override
    public User updateUser(String id, User user) {
        User userFounded = userRepository.getUserById(id);
        if (user != null) {
            User userUpdated = userRepository.updateUser(id, userFounded);
            return userUpdated;
        }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            userRepository.deleteUser(id);
        }
    }

    @Override
    public List<User> listUsers() {
        List<User> listUsers = userRepository.listUsers();
        return listUsers;
    }
}
