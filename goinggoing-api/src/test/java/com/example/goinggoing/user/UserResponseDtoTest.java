package com.example.goinggoing.user;

import com.example.goinggoing.user.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseDtoTest {

    @Test
    void testGetterAndSetter() {
        Long userId = 1L;
        String userNickname = "testName";
        String userEmail = "test@example.com";
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(userId);
        userResponseDto.setUserNickname(userNickname);
        userResponseDto.setUserEmail(userEmail);
        assertEquals(userId, userResponseDto.getUserId());
        assertEquals(userNickname, userResponseDto.getUserNickname());
        assertEquals(userEmail, userResponseDto.getUserEmail());
    }
}
