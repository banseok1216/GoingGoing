package com.example.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationSender {
    private final ExternalNotificationSender externalNotificationSender;
    public void sendNotification(
            Notification notification){
        externalNotificationSender.sendNotification(notification);
    }
}
