package dev.agitrubard.strategy.model.request;

import dev.agitrubard.strategy.model.enums.NotificationType;

public record NotificationRequest(String to, NotificationType type) {

    @Override
    public String toString() {
        return "{" +
                "\"to\": \"" + to + "\"," +
                "\"type\": \"" + type + "\"" +
                '}';
    }

}
