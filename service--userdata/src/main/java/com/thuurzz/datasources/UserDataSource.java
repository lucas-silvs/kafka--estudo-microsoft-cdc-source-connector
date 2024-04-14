package com.thuurzz.datasources;

import com.thuurzz.datasources.entities.UserEntity;

import java.util.List;

public interface UserDataSource {
    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(String id);

    UserEntity updateUser(String id, UserEntity userEntity);

    void deleteUser(String id);

    List<UserEntity> listUsers();

}
