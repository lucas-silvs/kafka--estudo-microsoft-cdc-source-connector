package com.thuurzz.datasources;

import com.thuurzz.datasources.entities.UserEntity;
import com.thuurzz.datasources.repository.UserRepositoryDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        UUID newUUID = UUID.fromString(id);
        UserEntity userEntity = userRepositoryDataSource.findById(newUUID);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(String id, UserEntity userEntity) {
        UUID newUUID = UUID.fromString(id);
        UserEntity user = userRepositoryDataSource.findById(newUUID);
        if (user == null) {
            System.out.print("User not found");
        }
        user.setEmail(userEntity.getEmail());
        user.setLastUpdated(LocalDate.now());
        userRepositoryDataSource.persist(user);
        return user;
    }

    @Override
    public void deleteUser(String id) {
        UUID newUUID = UUID.fromString(id);
        Boolean deleteStatus = userRepositoryDataSource.deleteById(newUUID);
        if (!deleteStatus) {
            System.out.print("An error occur at delete item");
        }
    }

    @Override
    public List<UserEntity> listUsers() {
        List<UserEntity> listUserEntities = userRepositoryDataSource.listAll();
        return listUserEntities;
    }
}
