package dev.agitrubard.strategy.controller;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;
import dev.agitrubard.strategy.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
class NotificationController {

    private final List<NotificationService> notificationServices;

    public NotificationController(List<NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest notificationRequest) {
        NotificationService notificationService = this.findNotificationService(notificationRequest.type());
        return notificationService.send(notificationRequest);
    }

    private NotificationService findNotificationService(NotificationType type) {
        return notificationServices.stream()
                .filter(notificationService -> notificationService.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Notification type not implemented"));
    }

}
