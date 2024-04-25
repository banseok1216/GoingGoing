package com.example.user.dto;

import com.example.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserResponse(
        Long userId,
        String userNickname,
        String userEmail
) {
    public static UserResponse of(User user) {
        return new UserResponse(
                user.getId().getValue(),
                user.getUserNickname(),
                user.getUserEmail()
        );
    }
}
