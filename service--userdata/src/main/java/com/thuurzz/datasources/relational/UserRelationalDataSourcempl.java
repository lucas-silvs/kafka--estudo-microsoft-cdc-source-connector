package com.thuurzz.datasources.relational;

import com.thuurzz.datasources.relational.entities.UserEntity;
import com.thuurzz.datasources.relational.panache.UserPanacheRepository;
import com.thuurzz.entities.User;
import com.thuurzz.repositories.UserRepository;
import com.thuurzz.repositories.mapper.UserEntityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRelationalDataSourcempl implements UserRepository {

    private final UserPanacheRepository userPanacheRepository;

    private static final UserEntityMapper mapper = UserEntityMapper.INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRelationalDataSourcempl.class);

    @Inject
    public UserRelationalDataSourcempl(UserPanacheRepository userPanacheRepository) {
        this.userPanacheRepository = userPanacheRepository;
    }

    @Transactional
    public User createUser(User user) {
        userPanacheRepository.persist(mapper.mapping(user));
        return user;

    }

    public User getUserById(String id) {
        UUID newUUID = UUID.fromString(id);
        UserEntity userEntity = userPanacheRepository.findById(newUUID);
        return mapper.mapping(userEntity);
    }

    public User updateUser(String id, User user) {

        UUID newUUID = UUID.fromString(id);
        UserEntity userEntity = userPanacheRepository.findById(newUUID);
        if (user != null) {
            user.setEmail(userEntity.getEmail());
            user.setLastUpdated(LocalDate.now());
            userPanacheRepository.persist(userEntity);
            return mapper.mapping(userEntity);
        }
        return null;
    }

    public void deleteUser(String id) {
        UUID newUUID = UUID.fromString(id);
        Boolean deleteStatus = userPanacheRepository.deleteById(newUUID);
        if (!deleteStatus) {
            LOGGER.error("An error occur at delete item");
        }
    }

    public List<User> listUsers() {
        List<UserEntity> listUserEntities = userPanacheRepository.listAll();
        return listUserEntities.stream().map(mapper::mapping).toList();
    }
}
