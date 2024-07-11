package dev.agitrubard.factory.service.impl;

import dev.agitrubard.factory.service.TwoFactorAuthenticationService;

class SmsAuthenticationServiceImpl implements TwoFactorAuthenticationService {

    @Override
    public String authenticate() {
        return "User authenticating via SMS...";
    }

}
