package com.example.user.implementation;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserCachedHandlerTest {
    private User firstUser;
    private User secondUser;
    @InjectMocks
    private UserCachedHandler userCachedHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        firstUser = User.withId(new User.UserId(123L), "user1", null, null, null, null);
        secondUser = User.withId(new User.UserId(456L), "user2", null, null, null, null);
    }
    @Test
    public void testPutAndGet() {
        userCachedHandler.put("123", firstUser);
        User retrievedUser = userCachedHandler.get("123");
        assertEquals(firstUser, retrievedUser);
    }

    @Test
    public void testRemove() {
        userCachedHandler.put("123", firstUser);

        userCachedHandler.remove("123");
        User retrievedUser = userCachedHandler.get("123");

        assertNull(retrievedUser);
    }

    @Test
    public void testClear() {
        userCachedHandler.put("user1", firstUser);
        userCachedHandler.put("user2", secondUser);

        userCachedHandler.clear();
        User retrievedUser1 = userCachedHandler.get("user1");
        User retrievedUser2 = userCachedHandler.get("user2");

        assertNull(retrievedUser1);
        assertNull(retrievedUser2);
    }
}
