package dev.agitrubard.strategy.model.request;

import dev.agitrubard.strategy.model.enums.NotificationType;

public class NotificationRequest {

    private String to;
    private NotificationType type;

    public NotificationRequest(String to, NotificationType type) {
        this.to = to;
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public NotificationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"to\": \"" + to + "\",\n" +
                "\"type\": \"" + type + "\"\n" +
                '}';
    }

}
