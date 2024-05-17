package com.example.user.implementation;

import com.example.error.BusinessException;
import com.example.routine.model.Routine;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserReaderTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserReader userReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testReadUser_Exception() {
        when(userRepository.readUserById(any(User.UserId.class))).thenReturn(null);
        assertThrows(BusinessException.class, () -> {
            userReader.readUser(any(User.UserId.class));
        });
    }

    @Test
    public void testReadUserRoutine_Exception() {
        when(userRepository.readUserRoutine(any(Routine.RoutineId.class))).thenReturn(null);
        assertThrows(BusinessException.class, () -> {
            userReader.readUserRoutine(any(Routine.RoutineId.class));
        });
    }
}
