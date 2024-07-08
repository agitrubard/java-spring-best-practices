package dev.agitrubard.strategy.service.impl;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;
import dev.agitrubard.strategy.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
class SmsNotificationServiceImpl implements NotificationService {

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "Sms notification sent to " + notificationRequest.to();
    }

}
