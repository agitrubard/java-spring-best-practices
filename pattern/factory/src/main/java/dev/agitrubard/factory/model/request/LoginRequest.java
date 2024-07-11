package dev.agitrubard.factory.model.request;

public record LoginRequest(String username, String password) {

    @Override
    public String toString() {
        return "{" +
                "\"username\": \"" + username + "\"," +
                "\"password\": \"" + password + "\"" +
                "}";
    }

}
