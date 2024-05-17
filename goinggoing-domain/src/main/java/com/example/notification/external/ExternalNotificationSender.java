package com.example.notification.external;

import com.example.notification.model.Notification;

public interface ExternalNotificationSender {
    void sendNotification(Notification notification);
}
