package dev.agitrubard.factory.repository.impl;

import dev.agitrubard.factory.model.entity.UserEntity;
import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;
import dev.agitrubard.factory.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class UserRepositoryImpl implements UserRepository {

    private final List<UserEntity> users = new ArrayList<>();

    public UserRepositoryImpl() {
        users.add(
                new UserEntity(
                        UUID.randomUUID().toString(),
                        "user1",
                        "password1",
                        "user1@agitrubard.dev",
                        "1234567890",
                        TwoFactorAuthenticationType.PASSKEY
                )
        );

        users.add(
                new UserEntity(
                        UUID.randomUUID().toString(),
                        "user2",
                        "password2",
                        "user2@agitrubard.dev",
                        "1234567891",
                        TwoFactorAuthenticationType.EMAIL
                )
        );

        users.add(
                new UserEntity(
                        UUID.randomUUID().toString(),
                        "user3",
                        "password3",
                        "user3@agitrubard.dev",
                        "1234567892",
                        TwoFactorAuthenticationType.SMS

                )
        );
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void save(UserEntity userEntity) {
        users.add(userEntity);
    }

}
