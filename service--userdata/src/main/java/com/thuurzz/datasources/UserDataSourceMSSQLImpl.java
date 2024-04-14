package com.thuurzz.datasources;

import com.thuurzz.datasources.entities.UserEntity;
import com.thuurzz.datasources.repository.UserRepositoryDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserDataSourceMSSQLImpl implements UserDataSource {

    @Inject
    UserRepositoryDataSource userRepositoryDataSource;

    @Override
    @Transactional
    public UserEntity createUser(UserEntity userEntity) {
        userRepositoryDataSource.persist(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUserById(String id) {
        return null;
    }

    @Override
    public UserEntity updateUser(String id, UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public List<UserEntity> listUsers() {
        List<UserEntity> listUserEntities = userRepositoryDataSource.listAll();
        return listUserEntities;
    }
}
