package com.example.user.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testHashedPassword() {
        User.Password originalPassword = new User.Password("password123");
        User.Password hashedPassword = originalPassword.hashPassword();
        User user = User.withoutId("nickname", "email@example.com", User.UserType.LOCAL,
                originalPassword, "deviceToken");
        assertTrue(user.getPassword().matches(hashedPassword.password()));
    }

    @Test
    public void testUpdate() {
        User originalUser = User.withoutId("nickname", "email@example.com", User.UserType.LOCAL,
                new User.Password("password123"), "deviceToken");
        User updateUser = User.withoutId("newNickname", "newemail@example.com", User.UserType.OAUTH_GOOGLE,
                new User.Password("newpassword456"), "newDeviceToken");
        User updatedUser = originalUser.update(updateUser);
        assertEquals("newNickname", updatedUser.getUserNickname());
        assertEquals("newemail@example.com", updatedUser.getUserEmail());
        assertEquals(User.UserType.OAUTH_GOOGLE, updatedUser.getUserType());
        assertTrue(updatedUser.getPassword().password().matches(updateUser.getPassword().password()));
        assertEquals("newDeviceToken", updatedUser.getDeviceToken());
    }
}
