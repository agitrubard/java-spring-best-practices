package dev.agitrubard.strategy.service;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;

public interface NotificationService {

    NotificationType getType();

    String send(NotificationRequest notificationRequest);

}
