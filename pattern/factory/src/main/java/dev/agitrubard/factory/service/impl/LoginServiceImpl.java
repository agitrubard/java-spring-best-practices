package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.model.entity.UserEntity;
import dev.agitrubard.factory.model.request.LoginRequest;
import dev.agitrubard.factory.repository.UserRepository;
import dev.agitrubard.factory.service.LoginService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationServiceFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final TwoFactorAuthenticationServiceFactory twoFactorAuthenticationServiceFactory;

    public LoginServiceImpl(UserRepository userRepository, TwoFactorAuthenticationServiceFactory twoFactorAuthenticationServiceFactory) {
        this.userRepository = userRepository;
        this.twoFactorAuthenticationServiceFactory = twoFactorAuthenticationServiceFactory;
    }

    @Override
    public String login(LoginRequest loginRequest) {

        Optional<UserEntity> userEntity = userRepository.findByUsername(loginRequest.username());

        if (userEntity.isEmpty()) {
            return "User not found!";
        }

        if (!userEntity.get().getPassword().equals(loginRequest.password())) {
            return "Invalid password!";
        }

        if (userEntity.get().isTwoFactorAuthenticationNotEnabled()) {
            return "Login success!";
        }

        TwoFactorAuthenticationService twoFactorAuthenticationService = twoFactorAuthenticationServiceFactory
                .create(userEntity.get().getTwoFactorAuthenticationType());
        return twoFactorAuthenticationService.authenticate();
    }

}
