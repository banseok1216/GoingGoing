package com.example.goinggoingdomain.user.service;

import com.example.error.BusinessException;
import com.example.group.service.GroupMemberService;
import com.example.group.service.GroupReader;
import com.example.routine.domain.Routine;
import com.example.user.domain.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserCachedReader;
import com.example.user.service.UserChecker;
import com.example.user.service.UserReader;
import com.example.user.service.UserWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserReader userReader;
    @InjectMocks
    private UserWriter userWriter;
    @InjectMocks
    private UserChecker userChecker;
    @InjectMocks
    private UserCachedReader userCachedReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPutAndGet() {
        User user = createfirstUser();
        userCachedReader.put("123", user);
        User retrievedUser = userCachedReader.get("123");
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testRemove() {
        User user = createfirstUser();
        userCachedReader.put("123", user);

        userCachedReader.remove("123");
        User retrievedUser = userCachedReader.get("123");

        assertNull(retrievedUser);
    }

    @Test
    public void testClear() {
        User user1 = createfirstUser();
        User user2 = createsecondUser();

        userCachedReader.put("user1", user1);
        userCachedReader.put("user2", user2);

        userCachedReader.clear();
        User retrievedUser1 = userCachedReader.get("user1");
        User retrievedUser2 = userCachedReader.get("user2");

        assertNull(retrievedUser1);
        assertNull(retrievedUser2);
    }
    @Test
    public void testReadUser_Exception() {
        when(userRepository.readUserById(new User.UserId(123L))).thenReturn(null);
        assertThrows(BusinessException.class, () -> {
            userReader.readUser(new User.UserId(123L));
        });
    }
    @Test
    public void testReadUserRoutine_Exception() {
        when(userRepository.readUserRoutine(new Routine.RoutineId(123L))).thenReturn(null);
        assertThrows(BusinessException.class, () -> {
            userReader.readUserRoutine(new Routine.RoutineId(123L));
        });
    }

    public User createfirstUser() {
        return User.withId(new User.UserId(123L), "user1", null, null, null, null);
    }

    public User createsecondUser() {
        return User.withId(new User.UserId(456L), "user2", null, null, null, null);
    }

}
