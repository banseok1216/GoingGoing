package com.example.user.implementation;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserCheckerTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserChecker userChecker;
    User fakeUser;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeUser =User.withId(new User.UserId(123L), "user1", null, null,  new User.Password("testPaasword"), null);

    }

    @Test
    public void 중복확인_중복되지_않은_사용자() {
        when(userRepository.check(any(User.class))).thenReturn(false);
        assertDoesNotThrow(() -> userChecker.isDuplicate(fakeUser));
    }

    @Test
    public void 중복확인_중복된_사용자() {
        when(userRepository.check(any(User.class))).thenReturn(true);
        assertThrows(BusinessException.class, () -> userChecker.isDuplicate(fakeUser));
    }
    @Test
    public void 유저가_존재하지_않는다() {
        when(userRepository.check(any(User.class))).thenReturn(false);
        assertFalse(userChecker.isNotExist(fakeUser));
    }
}

