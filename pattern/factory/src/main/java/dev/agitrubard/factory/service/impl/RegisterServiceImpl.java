package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.model.entity.UserEntity;
import dev.agitrubard.factory.model.request.RegisterRequest;
import dev.agitrubard.factory.repository.UserRepository;
import dev.agitrubard.factory.service.RegisterService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationServiceFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final TwoFactorAuthenticationServiceFactory twoFactorAuthenticationServiceFactory;

    public RegisterServiceImpl(UserRepository userRepository, TwoFactorAuthenticationServiceFactory twoFactorAuthenticationServiceFactory) {
        this.userRepository = userRepository;
        this.twoFactorAuthenticationServiceFactory = twoFactorAuthenticationServiceFactory;
    }

    @Override
    public String register(RegisterRequest registerRequest) {

        UserEntity userEntity = new UserEntity(
                UUID.randomUUID().toString(),
                registerRequest.username(),
                registerRequest.password(),
                registerRequest.emailAddress(),
                registerRequest.phoneNumber(),
                registerRequest.twoFactorAuthenticationType()
        );

        userRepository.save(userEntity);

        TwoFactorAuthenticationService twoFactorAuthenticationService = twoFactorAuthenticationServiceFactory
                .create(registerRequest.twoFactorAuthenticationType());
        return twoFactorAuthenticationService.authenticate();
    }

}
