package com.example.rabbitmq;

import com.example.notification.ExternalNotificationSender;
import com.example.notification.Notification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExternalNotificationHandler implements ExternalNotificationSender {
    private final ExternalMessageHandler externalMessageHandler;

    @Override
    public void sendNotification(Notification notification) {
        externalMessageHandler.notificationPush(NotificationMessage.of(notification));
    }
}
