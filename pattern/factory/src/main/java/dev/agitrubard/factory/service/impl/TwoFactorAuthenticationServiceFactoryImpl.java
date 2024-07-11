package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;
import dev.agitrubard.factory.service.TwoFactorAuthenticationService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationServiceFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class TwoFactorAuthenticationServiceFactoryImpl implements TwoFactorAuthenticationServiceFactory {

    @Override
    public TwoFactorAuthenticationService create(TwoFactorAuthenticationType twoFactorAuthenticationType) {

        TwoFactorAuthenticationType authenticationType = Optional.ofNullable(twoFactorAuthenticationType)
                .orElse(TwoFactorAuthenticationType.EMAIL);

        return switch (authenticationType) {
            case PASSKEY -> new PassKeyAuthenticationServiceImpl();
            case SMS -> new SmsAuthenticationServiceImpl();
            case EMAIL -> new EmailAuthenticationServiceImpl();
        };

    }

}
