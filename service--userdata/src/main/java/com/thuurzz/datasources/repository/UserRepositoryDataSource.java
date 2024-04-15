package com.thuurzz.datasources.repository;

import com.thuurzz.datasources.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserRepositoryDataSource implements PanacheRepositoryBase<UserEntity, UUID> {

}
