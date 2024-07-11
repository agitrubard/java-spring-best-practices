package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.service.TwoFactorAuthenticationService;

class PassKeyAuthenticationServiceImpl implements TwoFactorAuthenticationService {

    @Override
    public String authenticate() {
        return "User authenticating via Passkey...";
    }

}
