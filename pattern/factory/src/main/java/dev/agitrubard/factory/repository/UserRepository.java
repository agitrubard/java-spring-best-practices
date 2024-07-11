package dev.agitrubard.factory.repository;

import dev.agitrubard.factory.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

    void save(UserEntity userEntity);

}
