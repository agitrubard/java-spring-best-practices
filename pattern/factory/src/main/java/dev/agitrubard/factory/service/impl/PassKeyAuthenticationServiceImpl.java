package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.service.TwoFactorAuthenticationService;

import org.springframework.stereotype.Service;

@Service(value = "PASSKEY")
class PassKeyAuthenticationServiceImpl implements TwoFactorAuthenticationService {

  @Override
  public String authenticate() {
    return "User authenticating via Passkey...";
  }

}
