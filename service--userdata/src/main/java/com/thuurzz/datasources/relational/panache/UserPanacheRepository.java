package com.thuurzz.datasources.relational.panache;

import com.thuurzz.datasources.relational.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserPanacheRepository implements PanacheRepositoryBase<UserEntity, UUID> {

}
