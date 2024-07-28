package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;
import dev.agitrubard.factory.service.TwoFactorAuthenticationService;
import dev.agitrubard.factory.service.TwoFactorAuthenticationServiceFactory;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
class TwoFactorAuthenticationServiceFactoryImpl implements TwoFactorAuthenticationServiceFactory {

  private final ApplicationContext applicationContext;

  public TwoFactorAuthenticationServiceFactoryImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public TwoFactorAuthenticationService create(TwoFactorAuthenticationType twoFactorAuthenticationType) {

    TwoFactorAuthenticationType authenticationType = Optional.ofNullable(twoFactorAuthenticationType)
        .orElse(TwoFactorAuthenticationType.EMAIL);

    return (TwoFactorAuthenticationService) applicationContext.getBean(authenticationType.name());
  }

}
