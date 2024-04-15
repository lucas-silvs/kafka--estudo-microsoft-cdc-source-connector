package com.thuurzz.repositories;

import com.thuurzz.datasources.UserDataSource;
import com.thuurzz.datasources.entities.UserEntity;
import com.thuurzz.entities.User;
import com.thuurzz.repositories.mapper.UserEntityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    private UserDataSource userDataSource;

    private final UserEntityMapper mapper = UserEntityMapper.INSTANCE;

    @Inject
    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public User createUser(User user) {
        UserEntity newUserEntity = userDataSource.createUser(mapper.mapping(user));
        User newUser = mapper.mapping(newUserEntity);
        return newUser;
    }

    @Override
    public User getUserById(String id) {
        UserEntity userEntity = userDataSource.getUserById(id);
        User user = mapper.mapping(userEntity);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        UserEntity userEntity = userDataSource.updateUser(id, mapper.mapping(user));
        User userUpdated = mapper.mapping(userEntity);
        return userUpdated;
    }

    @Override
    public void deleteUser(String id) {
        userDataSource.deleteUser(id);
    }

    @Override
    public List<User> listUsers() {
        List<UserEntity> userEntityList = userDataSource.listUsers();
        return userEntityList.stream().map(mapper::mapping).toList();
    }
}
