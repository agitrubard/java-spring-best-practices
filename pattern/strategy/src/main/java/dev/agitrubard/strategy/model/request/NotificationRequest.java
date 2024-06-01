package dev.agitrubard.strategy.model.request;

import dev.agitrubard.strategy.model.enums.NotificationType;

public class NotificationRequest {

    private String to;
    private NotificationType type;

    public String getTo() {
        return to;
    }

    public NotificationType getType() {
        return type;
    }

}
