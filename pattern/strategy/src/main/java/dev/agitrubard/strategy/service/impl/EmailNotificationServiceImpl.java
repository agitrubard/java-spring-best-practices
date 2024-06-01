package dev.agitrubard.strategy.service.impl;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;
import dev.agitrubard.strategy.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
class EmailNotificationServiceImpl implements NotificationService {

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "Email notification sent to " + notificationRequest.getTo();
    }

}
