package dev.agitrubard.strategy.service.impl;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;
import dev.agitrubard.strategy.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
class PushNotificationServiceImpl implements NotificationService {

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "Push notification sent to " + notificationRequest.getTo();
    }

}
