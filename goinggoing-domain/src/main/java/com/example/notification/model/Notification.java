package com.example.notification.model;

import com.example.user.model.User;
import lombok.Getter;

public record Notification(
        String deviceToken,
        User user,
        Long groupId,
        Type type

) {
    public enum Type {
        START("일정 시작"),
        DONE("일정 종료");
        public final String message;
        Type(String message) {
            this.message = message;
        }
    }
}
