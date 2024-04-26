package com.example.user.dto;

import com.example.user.User;

public record UserResponse(
        Long userId,
        String userNickname,
        String userEmail
) {
    public static UserResponse of(User user) {
        return new UserResponse(
                user.getId().value(),
                user.getUserNickname(),
                user.getUserEmail()
        );
    }
}
