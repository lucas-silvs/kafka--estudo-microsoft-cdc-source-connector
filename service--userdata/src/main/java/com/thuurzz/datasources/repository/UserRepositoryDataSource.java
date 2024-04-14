package com.thuurzz.datasources.repository;

import com.thuurzz.datasources.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryDataSource implements PanacheRepository<UserEntity> {
}
