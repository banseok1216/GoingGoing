package com.example.notification.implementation;

import com.example.notification.external.ExternalNotificationSender;
import com.example.notification.model.Notification;
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
