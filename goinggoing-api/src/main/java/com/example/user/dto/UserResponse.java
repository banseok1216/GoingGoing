package com.example.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserResponse(
        Long userId,
        String userNickname,
        String userEmail
) {
}
