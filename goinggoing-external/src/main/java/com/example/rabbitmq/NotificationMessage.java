package com.example.rabbitmq;

import com.example.group.model.Group;
import com.example.notification.Notification;
import com.example.user.model.User;

public record NotificationMessage(
        String deviceToken,
        Long groupId,
        String content
) {
    public static NotificationMessage of(Notification notification) {
        return new NotificationMessage(notification.deviceToken(), notification.groupId(),notification.user().getUserNickname() + "님의 일정이 " + notification.type().message + "되었습니다.");
    }
}