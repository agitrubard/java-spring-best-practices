package dev.agitrubard.factory.model.entity;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;

public class UserEntity {

    private String id;
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private TwoFactorAuthenticationType twoFactorAuthenticationType;

    public UserEntity(String id,
                      String username,
                      String password,
                      String emailAddress,
                      String phoneNumber,
                      TwoFactorAuthenticationType twoFactorAuthenticationType) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.twoFactorAuthenticationType = twoFactorAuthenticationType;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TwoFactorAuthenticationType getTwoFactorAuthenticationType() {
        return twoFactorAuthenticationType;
    }

    public boolean isTwoFactorAuthenticationNotEnabled() {
        return this.twoFactorAuthenticationType == null;
    }

}
