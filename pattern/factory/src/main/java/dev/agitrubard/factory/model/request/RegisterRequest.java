package dev.agitrubard.factory.model.request;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;

public record RegisterRequest(String username,
                              String password,
                              String emailAddress,
                              String phoneNumber,
                              TwoFactorAuthenticationType twoFactorAuthenticationType) {

    @Override
    public String toString() {

        if (this.twoFactorAuthenticationType == null) {
            return "{" +
                    "\"username\": \"" + username + "\"," +
                    "\"password\": \"" + password + "\"," +
                    "\"emailAddress\": \"" + emailAddress + "\"," +
                    "\"phoneNumber\": \"" + phoneNumber + "\"" +
                    '}';
        }

        return "{" +
                "\"username\": \"" + username + "\"," +
                "\"password\": \"" + password + "\"," +
                "\"emailAddress\": \"" + emailAddress + "\"," +
                "\"phoneNumber\": \"" + phoneNumber + "\"," +
                "\"twoFactorAuthenticationType\": \"" + twoFactorAuthenticationType + "\"" +
                '}';
    }

}
