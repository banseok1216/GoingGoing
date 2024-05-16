package com.example.rabbitmq;

import com.example.user.model.User;

public record UserMessage(
        Long userId
) {
    public static UserMessage of(User user) {
        return new UserMessage(user.getId().value());
    }
}
