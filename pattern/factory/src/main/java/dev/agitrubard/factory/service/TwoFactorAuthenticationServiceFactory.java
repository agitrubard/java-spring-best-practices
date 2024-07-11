package dev.agitrubard.factory.service;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;

public interface TwoFactorAuthenticationServiceFactory {

    TwoFactorAuthenticationService create(TwoFactorAuthenticationType authenticationType);

}
